# -*- coding: utf-8 -*-


######## Picamera Object Detection Using Tensorflow Classifier ##########
# Description:
## Some of the code is copied from Google's example at
## https://github.com/tensorflow/models/blob/master/research/object_detection/object_detection_tutorial.ipynb
## and some is copied from Dat Tran's example at
## https://github.com/datitran/object_detector_app/blob/master/object_detection_app.py
## but I changed it to make it more understandable to me.

#Import packages
import os
import cv2
import numpy as np
from picamera.array import PiRGBArray
from picamera import PiCamera
import tensorflow as tf
import argparse
import sys
import pandas as pd
from decimal import *

#Import les librairies
from datetime import datetime
from datetime import date
import csv

#Librairie utilisé pour le poids
from os import path
import time
import RPi.GPIO as GPIO
from hx711 import HX711

#Nom des fichiers factures
#name = datetime.now().strftime('%Y-%m-%d %Hh%Mm%Ss')
name = 'facture_affichage'
name_erreur = 'erreur'

#name_real_time = datetime.now().strftime('%Y-%m-%d %Hh%Mm%Ss_real_time')
#name = datetime.now().strftime('%Y-%m-%d %H:%M:%S.%f')
if path.exists('/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt'):
    os.remove('/home/pi/tensorflow1/models/research/object_detection/factures/facture_affichage.txt')
#Lecture de la DataFrame de prix
df = pd.read_csv('/home/pi/tensorflow1/models/research/object_detection/database.csv', sep = ';', index_col = 0)
#df = pd.read_csv('database1.csv', sep = ';', index_col = 0)

def generateur_facture_fichier(liste_fruits, quantite_fruits, poids_fruits, name_file):
    filename = "/home/pi/tensorflow1/models/research/object_detection/factures/archives/%s_old.txt" % name_file
    f = open(filename , 'w')
    
    print('\n')
    f.write('Qte' + '\t' + 'Type' + '\t' + 'Design' + '\t' + 'Prix/u' + '\t' + 'Prix HT' + '\n')
    
    for i in range(len(liste_fruits)) :
        if df[liste_fruits[i]]['Type vente'] == 'poids':
            val_prix = Decimal(df[liste_fruits[i]]['Prix'])
            val_poids = Decimal(poids_fruits[i])
            val_poids_clean = val_poids.quantize(Decimal('.001'), rounding = ROUND_HALF_UP)
            prix_total = val_prix*val_poids_clean
            prix_total_clean = prix_total.quantize(Decimal('.01'), rounding = ROUND_HALF_UP)

            f.write(str(val_poids_clean) + '\t' + 'kg' + '\t' + str(liste_fruits[i]) + '\t' + str(val_prix) + '\t' + str(prix_total_clean) + '\n')
            #f.write(str(poids_fruits[i]) + '\t' + 'kg' + '\t' + str(liste_fruits[i]) + '\t' + str(df[liste_fruits[i]]['Prix']) + '\t' + str(float(df[liste_fruits[i]]['Prix'])*poids_fruits[i]) + '\n')
        else:
            f.write(str(quantite_fruits[i]) + '\t' + 'piece' + '\t' + str(liste_fruits[i]) + '\t' + str(df[liste_fruits[i]]['Prix']) + '\t' + str(float(df[liste_fruits[i]]['Prix'])*quantite_fruits[i]) + '\n')
    
    f.close()
    
def generateur_first_line(name_file):
    filename = "/home/pi/tensorflow1/models/research/object_detection/factures/%s.txt" % name_file
    f = open(filename , 'w')
    f.write('Qte' + '\t' + 'Type' + '\t' + 'Design' + '\t' + 'Prix/u' + '\t' + 'Prix HT' + '\n')
    f.close()
    
def generateur_erreur(name_file):
    filename = "/home/pi/tensorflow1/models/research/object_detection/erreur/%s.txt" % name_file
    f = open(filename , 'w')
    f.write('1')
    f.close()

def clean_facture_fichier(name_file):

    old_filename = "/home/pi/tensorflow1/models/research/object_detection/factures/archives/%s_old.txt" % name_file
    filename = "/home/pi/tensorflow1/models/research/object_detection/factures/%s.txt" % name_file

    d = dict()
    
    #with open(old_filename, "r+") as inputfile, open(filename, "w") as outputfile:
    inputfile = open(old_filename,"r+")
    outputfile = open(filename,"w")
    csv_reader = csv.reader(inputfile, delimiter='\t')
    csv_writer = csv.writer(outputfile, delimiter='\t')

    #skip la premiere ligne des titres avec designation, prix etc..
    next(csv_reader)

    # Ce fichier ne possede pas de lignes identiques :
    # On regarde si les labels matchent, si c'est le cas on va append la quantite 
    # recue pour un label

    #Dictionnaire : Label : [Quantite, type vente, prix HT, prix tot]
    #il faut alors append la quantite et le prix tot

    for lines in csv_reader:
        if any(lines): #Si on a une ligne on l'analyse
            #print(lines)
            if lines[2] == 'autre':
                pass
            elif lines[2] in d:   #existe deja, et append des valeurs dans le dictionnaire
                l = d.get(lines[2])
                if (l[1] == 'kg') and (float(lines[0]) == float(l[0])): #supprime les doublons kilogramme
                    pass
                    #print('Même poids donc suppréssion des doublons')
                elif (l[1] == 'kg'):
                    #print('Même fruits mais poids différents donc on additionne les poids')
                    
                    #L'intermédiaire avec les nom de variable permet de corriger une erreur de chiffres significatifs.
                    #Intermédiaire pour l'élement 0 (Poids)
                    element0 = Decimal(lines[0])
                    elem0 = Decimal(l[0])
                    elements0_totale = element0 + elem0
                    elements0_totale_clean = elements0_totale.quantize(Decimal('.001'), rounding = ROUND_HALF_UP)
                    
                    #Intermédiaire pour l'élement 3 (Prix Total)
                    element3 = Decimal(lines[3])
                    elem3 = Decimal(l[3])
                    elements3_totale = element3 + elem3
                    elements3_totale_clean = elements3_totale.quantize(Decimal('.01'), rounding = ROUND_HALF_UP)
                    
                    l[0] = elements0_totale_clean
                    l[3] = elements3_totale_clean
                else:
                    #print('Même fruits de type pièce donc on somme les quantités')
                    
                    l[0] = int(lines[0]) + int(l[0])
                    
                    element3 = Decimal(lines[3])
                    elem3 = Decimal(l[3])
                    elements3_totale = element3 + elem3
                    elements3_totale_clean = elements3_totale.quantize(Decimal('.01'), rounding = ROUND_HALF_UP)
                    
                    l[3] = elements3_totale_clean
                    d[lines[2]] = l
            else:               #ajouter au dictionnaire
                d[lines[2]] = [lines[0], lines[1], lines[3], lines[4]]

    #ecrire la premiere ligne des titres avec designation, prix etc
    csv_writer.writerow(['Qte','Type', 'Design','Prix/u','Prix HT'])
    #ecrire le dico dans le fichier CSV
    for keys, values in d.items():
        tab1=[]
        tab1.append(values)
        flattened = [val for sublist in tab1 for val in sublist]
        flattened.insert(2, keys)
        csv_writer.writerow(flattened)
        #print(flattened)
        tab1.clear()
        flattened.clear()
    outputfile.close()
    inputfile.close()

def message_facture(name_file):
        filename = "/home/pi/tensorflow1/models/research/object_detection/factures/%s.txt" % name_file  #Ajouter une direction /facture ?
        f = open(filename , 'r')
        chaine = f.read()
        print(chaine)
        f.close()

def message_facture_final(name_file):
        filename = "/home/pi/tensorflow1/models/research/object_detection/factures/%s.txt" % name_file  #Ajouter une direction /facture ?
        f = open(filename , 'r')
        chaine = f.read()
        print("Voici le récapitulatif de votre facture :")
        print(chaine)
        print("Facture finale générée, Nom du fichier : %s" % name_file)
        f.close()

def get_poids():
    file_poids = open("/home/pi/tensorflow1/models/research/object_detection/hx711/temp/pesee_java.txt","r")
    str_poids = file_poids.readlines()
    val_poids = int(str_poids[0])
    val_poids_ajuste = val_poids/1000
    file_poids.close()
    return(val_poids_ajuste)

def order_receive():
    val_order = 0
    if path.exists("/home/pi/tensorflow1/models/research/object_detection/ordres/filename.txt"):
        file_order = open("/home/pi/tensorflow1/models/research/object_detection/ordres/filename.txt","r")
        str_order = file_order.readlines()
        val_order = int(str_order[0])
        file_order.close()
        os.remove("/home/pi/tensorflow1/models/research/object_detection/ordres/filename.txt")
    return(val_order == 1)

def end_receive():
    val_order = 0
    if path.exists("/home/pi/tensorflow1/models/research/object_detection/ordres/endfile.txt"):
        file_order = open("/home/pi/tensorflow1/models/research/object_detection/ordres/endfile.txt","r")
        str_order = file_order.readlines()
        val_order = int(str_order[0])
        file_order.close()
        os.remove("/home/pi/tensorflow1/models/research/object_detection/ordres/endfile.txt")
    return(val_order == 1)
    

# Set up camera constants
IM_WIDTH = 1280
IM_HEIGHT = 720
#IM_WIDTH = 640    Use smaller resolution for
#IM_HEIGHT = 480   slightly faster framerate

# Selection de la camera
camera_type = 'picamera'

# This is needed since the working directory is the object_detection folder.
sys.path.append('..')

# Import utilites
from utils import label_map_util
from utils import visualization_utils as vis_util

# Name of the directory containing the object detection module we're using
MODEL_NAME = 'ssdlite_mobilenet_v2_coco_2018_05_09'
#MODEL_NAME = 'output_inference_graph_v2'

# Grab path to current working directory
CWD_PATH = os.getcwd()

# Path to frozen detection graph .pb file, which contains the model that is used
# for object detection.
#PATH_TO_CKPT = os.path.join(CWD_PATH,MODEL_NAME,'frozen_inference_graph.pb')
#PATH_TO_CKPT = '/home/pi/tensorflow1/models/research/object_detection/output_inference_graph_v2/frozen_inference_graph.pb'
PATH_TO_CKPT = '/home/pi/tensorflow1/models/research/object_detection/ssdlite_mobilenet_v2_coco_2018_05_09/frozen_inference_graph.pb'

# Path to label map file
#PATH_TO_LABELS = os.path.join(CWD_PATH,'data','mscoco_label_map.pbtxt')
#PATH_TO_LABELS = os.path.join(CWD_PATH,'data','label_map.pbtxt')
#PATH_TO_LABELS = '/home/pi/tensorflow1/models/research/object_detection/data/label_map.pbtxt'
PATH_TO_LABELS = '/home/pi/tensorflow1/models/research/object_detection/data/mscoco_label_map_test.pbtxt'


#PATH_TO_FACTURE = os.path.join(CWD_PATH,'factures', "%s.txt" % name)

# Number of classes the object detector can identify
NUM_CLASSES = 90
#NUM_CLASSES = 3

## Load the label map.
# Label maps map indices to category names, so that when the convolution
# network predicts `5`, we know that this corresponds to `airplane`.
# Here we use internal utility functions, but anything that returns a
# dictionary mapping integers to appropriate string labels would be fine
label_map = label_map_util.load_labelmap(PATH_TO_LABELS)
categories = label_map_util.convert_label_map_to_categories(label_map, max_num_classes=NUM_CLASSES, use_display_name=True)
category_index = label_map_util.create_category_index(categories)

# Load the Tensorflow model into memory.
detection_graph = tf.Graph()
with detection_graph.as_default():
    od_graph_def = tf.GraphDef()
    with tf.gfile.GFile(PATH_TO_CKPT, 'rb') as fid:
        serialized_graph = fid.read()
        od_graph_def.ParseFromString(serialized_graph)
        tf.import_graph_def(od_graph_def, name='')
    sess = tf.Session(graph=detection_graph)

# Define input and output tensors (i.e. data) for the object detection classifier

# Input tensor is the image
image_tensor = detection_graph.get_tensor_by_name('image_tensor:0')

# Output tensors are the detection boxes, scores, and classes
# Each box represents a part of the image where a particular object was detected
detection_boxes = detection_graph.get_tensor_by_name('detection_boxes:0')

# Each score represents level of confidence for each of the objects.
# The score is shown on the result image, together with the class label.
detection_scores = detection_graph.get_tensor_by_name('detection_scores:0')
detection_classes = detection_graph.get_tensor_by_name('detection_classes:0')

# Number of objects detected
num_detections = detection_graph.get_tensor_by_name('num_detections:0')

# Initialize frame rate calculation
frame_rate_calc = 1
freq = cv2.getTickFrequency()
font = cv2.FONT_HERSHEY_SIMPLEX

# Initialize Picamera and grab reference to the raw capture
camera = PiCamera()
camera.resolution = (IM_WIDTH,IM_HEIGHT)
camera.framerate = 10
rawCapture = PiRGBArray(camera, size=(IM_WIDTH,IM_HEIGHT))
rawCapture.truncate(0)

#Liste finale dans laquelle on enregistre chaque saisie
liste_fruits_finale = []
poids_fruits_finale = []
generateur_first_line(name)

for frame1 in camera.capture_continuous(rawCapture, format="bgr",use_video_port=True):

    t1 = cv2.getTickCount()
    # Acquire frame and expand frame dimensions to have shape: [1, None, None, 3]
    # i.e. a single-column array, where each item in the column has the pixel RGB value
    frame = np.copy(frame1.array)
    frame.setflags(write=1)
    frame_expanded = np.expand_dims(frame, axis=0)

    # Perform the actual detection by running the model with the image as input
    (boxes, scores, classes, num) = sess.run(
        [detection_boxes, detection_scores, detection_classes, num_detections],
        feed_dict={image_tensor: frame_expanded})
    
    # Afficher le résultat de la détection ('visualisation des résultats')
    vis_util.visualize_boxes_and_labels_on_image_array(
        frame,
        np.squeeze(boxes),
        np.squeeze(classes).astype(np.int32),
        np.squeeze(scores),
        category_index,
        use_normalized_coordinates=True,
        line_thickness=8,
        min_score_thresh=0.40)

    cv2.putText(frame,"FPS: {0:.2f}".format(frame_rate_calc),(30,50),font,1,(255,255,0),2,cv2.LINE_AA)
    
    #print(objects)
    #print(len(np.where(scores[0] > threshold)[0])/num_detections[0])
    #print(liste_fruits_real_time)
    #print(liste_fruits_finale)
    #print(poids_fruits_finale)
    #print(get_poids())
    
    # All the results have been drawn on the frame, so it's time to display it.
    dim = (1024,600)
    resized = cv2.resize(frame,dim, interpolation = cv2.INTER_AREA)
    cv2.imshow('NEURALNETcamera', resized)
    
    t2 = cv2.getTickCount()
    time1 = (t2-t1)/freq
    frame_rate_calc = 1/time1
    
    objects = []
    liste_fruits_real_time = []
    threshold = 0.5 # in order to get higher percentages you need to lower this number; usually at 0.01 you get 100% predicted objects
    action = cv2.waitKey(1)
    
    # Pour ajouter un fruit pressez 'a'
    if action == ord('a'):
        for index, value in enumerate(classes[0]):
            object_dict = {}
            if scores[0, index] > threshold:
                object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                objects.append(object_dict)
                liste_fruits_real_time.append((category_index.get(value)).get('name'))
        print(liste_fruits_real_time)
        erreur = 0
                #Supprime les objects détecter en autres
#                 for i in range(len(liste_fruits_real_time)):
#                     if (df[liste_fruits_real_time[i]]['Type vente'] == 'autre'):
#                         del liste_fruits_real_time[i];
        if (len(liste_fruits_real_time) >= 2) and df[liste_fruits_real_time[0]]['Type vente'] == 'piece':
            for i in range(1, len(liste_fruits_real_time)):
                if df[liste_fruits_real_time[i]]['Type vente'] == 'poids':
                    print('Erreur : Type Poids et Pièce détécté.')
                    generateur_erreur(name_erreur)
                    erreur = 1
                    break;
            if erreur != 1:
                for index, value in enumerate(classes[0]):
                    object_dict = {}
                    if scores[0, index] > threshold:
                        object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                        objects.append(object_dict)
                        liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                        poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                        #poids_fruits_finale = [2] * len(liste_fruits_finale)
                        quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                        generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                        clean_facture_fichier(name)
                        message_facture(name)
                erreur = 0
                
        elif (len(liste_fruits_real_time) >= 2) and df[liste_fruits_real_time[0]]['Type vente'] == 'poids':
            for i in range(1, len(liste_fruits_real_time)):
                if df[liste_fruits_real_time[i]]['Type vente'] == 'piece':
                    print('Erreur : Type Poids et Pièce détécté.')
                    generateur_erreur(name_erreur)
                    erreur = 1
                    break;
            if erreur != 1:
                for index, value in enumerate(classes[0]):
                    object_dict = {}
                    if scores[0, index] > threshold:
                        object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                        objects.append(object_dict)
                        liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                        poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                        #poids_fruits_finale = [2] * len(liste_fruits_finale)
                        quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                        generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                        clean_facture_fichier(name)
                        message_facture(name)
                erreur = 0
        else:
            for index, value in enumerate(classes[0]):
                object_dict = {}
                if scores[0, index] > threshold:
                    object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                    objects.append(object_dict)
                    liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                    poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                    #poids_fruits_finale = [2] * len(liste_fruits_finale)
                    quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                    generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                    clean_facture_fichier(name)
                    message_facture(name)

    if order_receive():
        for index, value in enumerate(classes[0]):
            object_dict = {}
            if scores[0, index] > threshold:
                object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                objects.append(object_dict)
                liste_fruits_real_time.append((category_index.get(value)).get('name'))
        print(liste_fruits_real_time)
        erreur = 0
                #Supprime les objects détecter en autres
#                 for i in range(len(liste_fruits_real_time)):
#                     if (df[liste_fruits_real_time[i]]['Type vente'] == 'autre'):
#                         del liste_fruits_real_time[i];
        if (len(liste_fruits_real_time) >= 2) and df[liste_fruits_real_time[0]]['Type vente'] == 'piece':
            for i in range(1, len(liste_fruits_real_time)):
                if df[liste_fruits_real_time[i]]['Type vente'] == 'poids':
                    print('Erreur : Type Poids et Pièce détécté.')
                    generateur_erreur(name_erreur)
                    erreur = 1
                    break;
            if erreur != 1:
                for index, value in enumerate(classes[0]):
                    object_dict = {}
                    if scores[0, index] > threshold:
                        object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                        objects.append(object_dict)
                        liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                        poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                        #poids_fruits_finale = [2] * len(liste_fruits_finale)
                        quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                        generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                        clean_facture_fichier(name)
                        message_facture(name)
                erreur = 0
                
        elif (len(liste_fruits_real_time) >= 2) and df[liste_fruits_real_time[0]]['Type vente'] == 'poids':
            for i in range(1, len(liste_fruits_real_time)):
                if df[liste_fruits_real_time[i]]['Type vente'] == 'piece':
                    print('Erreur : Type Poids et Pièce détécté.')
                    generateur_erreur(name_erreur)
                    erreur = 1
                    break;
            if erreur != 1:
                for index, value in enumerate(classes[0]):
                    object_dict = {}
                    if scores[0, index] > threshold:
                        object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                        objects.append(object_dict)
                        liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                        poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                        #poids_fruits_finale = [2] * len(liste_fruits_finale)
                        quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                        generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                        clean_facture_fichier(name)
                        message_facture(name)
                erreur = 0
        else:
            for index, value in enumerate(classes[0]):
                object_dict = {}
                if scores[0, index] > threshold:
                    object_dict[(category_index.get(value)).get('name').encode('utf8')] = scores[0, index]
                    objects.append(object_dict)
                    liste_fruits_finale.append((category_index.get(value)).get('name')) #Liste de fruits
                    poids_fruits_finale.append(get_poids()) #Liste poids -- ajoute un poids pour chaque fruit détecter
                    #poids_fruits_finale = [2] * len(liste_fruits_finale)
                    quantite_fruits = [1] * len(liste_fruits_finale) #Quantité mise à 1 par défaut
                    generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
                    clean_facture_fichier(name)
                    message_facture(name)


    # Pour quitter pressez 'q'
    if action == ord('q'):
        break
    
    # Pour clean la liste finale pressez 'c'
    if action == ord('c'):
        liste_fruits_finale = []
        poids_fruits_finale = []
        quantite_fruits = []
        
    # Pour clean la liste finale pressez 'c'
    if end_receive():
        liste_fruits_finale = []
        poids_fruits_finale = []
        quantite_fruits = []
        generateur_first_line(name)

    rawCapture.truncate(0)

camera.close()
cv2.destroyAllWindows()

#quantite_fruits = [1] * len(liste_fruits_finale) # Il faut pouvoir récupérer de manière dynamique la quantité de chaque fruit ainsi que son poids
#poids_fruits = [2] * len(liste_fruits_finale) #essayer de recup le poids au moment du if action

#Appel de la fonction qui affiche les fruits et leur prix
#generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits)
#generateur_facture_fichier(liste_fruits_finale, quantite_fruits, poids_fruits_finale, name)
#clean_facture_fichier(name)
message_facture_final(name)

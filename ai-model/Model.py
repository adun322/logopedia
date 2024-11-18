import os
import numpy as np
import random
import csv
import librosa
import tensorflow as tf
from keras import Sequential, regularizers
from tensorflow.keras.layers import Conv2D, MaxPooling2D, Flatten, Dense, Dropout, BatchNormalization
from sklearn.model_selection import train_test_split

# Параметры
SAMPLE_RATE = 16000
DURATION = 15
N_MFCC = 15
N_CLASSES = 2  # количество# классов: картавый или не картавый
MAX_LEN = int(SAMPLE_RATE * DURATION / 512) + 1  # максимальная длина MFCC


def add_noise(data, noise_factor=0.005):
    noise = np.random.randn(data.shape[0])
    augmented_data = data + noise_factor * noise
    return augmented_data


def change_speed(data, speed_factor=1.2):
    return librosa.effects.time_stretch(data, rate=speed_factor)


def load_audio_files(file_paths):
    data = []
    for file_path in file_paths:
        y, sr = librosa.load(file_path, sr=SAMPLE_RATE, duration=DURATION)
        #y = add_noise(y, factors[0])
        #y = change_speed(y, abs(factors[1]))
        mfcc = librosa.feature.mfcc(y=y, sr=sr)
        if mfcc.shape[1] < MAX_LEN:
            pad_width = MAX_LEN - mfcc.shape[1]
            mfcc = np.pad(mfcc, pad_width=((0, 0), (0, pad_width)), mode='constant')
        else:

            mfcc = mfcc[:, :MAX_LEN]
        mfcc = np.expand_dims(mfcc, axis=-1)  # добавляем дополнительное измерение для каналов

        data.append(mfcc)
    return np.array(data, dtype=np.float32)

def load_data(data_dir):

    file_paths = []
    labels = []

    with open(data_dir) as file:
        reader = csv.reader(file, delimiter=',')
        for row in reader:
            file_path = f'train/{row[0]}'
            file_paths.append(file_path)
            labels.append(row[1])
    print(len(file_paths))
    X = load_audio_files(file_paths)
    y = np.array(labels, dtype=np.float32)
    return X, y


# Подготовка данных
data_dir = 'train_gt.csv'
X, y = load_data(data_dir)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
print(X[0].shape)
# Модель
model = Sequential([
    Conv2D(16, (3, 3), activation='relu', kernel_regularizer=regularizers.l2(0.001), input_shape=(N_MFCC, int(SAMPLE_RATE * DURATION / 512) + 1, 1)),
    MaxPooling2D((1, 2)),
    Conv2D(32, (3, 3), activation='relu', kernel_regularizer=regularizers.l2(0.001)),
    MaxPooling2D((1, 2)),
    Flatten(),
    Dense(256, activation='relu'),
    Dropout(0.5),
    Dense(N_CLASSES, activation='softmax')
])
optimizer = tf.keras.optimizers.Adam(learning_rate=0.001)
model.compile(optimizer=optimizer, loss='sparse_categorical_crossentropy', metrics=["accuracy"])

log_dir = '/content/drive/My Drive/logs/'
tb_callback = tf.keras.callbacks.TensorBoard(log_dir=log_dir, histogram_freq=1)

for i in range(40):
    # Обучение модели
    model.fit(X_train, y_train, epochs=1, batch_size=32, validation_data=(X_test, y_test), callbacks=[tb_callback])

    # Оценка модели
    loss, accuracy = model.evaluate(X_test, y_test)
    print(f'Test Loss: {loss}')
    print(f'Test Accuracy: {accuracy}')
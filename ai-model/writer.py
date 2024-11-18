from Model import model
import csv
import librosa
import numpy as np


SAMPLE_RATE = 8000
DURATION = 15
N_MFCC = 20
N_CLASSES = 2  # количество# классов: картавый или не картавый
MAX_LEN = int(SAMPLE_RATE * DURATION / 512) + 1  # максимальная длина MFCC


data = []
with open("test-2.csv") as file:
    reader = csv.reader(file, delimiter=',')
    for row in reader:
        filename = row[0]
        y, sr = librosa.load(f'final_test/{filename}', sr=SAMPLE_RATE, duration=DURATION)
        mfcc = librosa.feature.mfcc(y=y, sr=sr, n_mfcc=N_MFCC)
        if mfcc.shape[1] < MAX_LEN:
            pad_width = MAX_LEN - mfcc.shape[1]
            mfcc = np.pad(mfcc, pad_width=((0, 0), (0, pad_width)), mode='constant')
        else:
            mfcc = mfcc[:, :MAX_LEN]
        mfcc = np.expand_dims(mfcc, axis=-1)
        mfcc = np.expand_dims(mfcc, axis=0)
        mfcc = np.array(mfcc, dtype=np.float32)

        ans = np.argmax(model(mfcc))
        print(ans)
        data.append([filename, ans])

with open("test-2.csv", 'w', newline='') as file:
    writer = csv.writer(file, delimiter=',')
    for row in data:
        writer.writerow(row)

import matplotlib.pyplot as plt


medias = [870, 2324, 2472, 3968, 4533, 6351, 5949, 8358, 9809, 13597]
n = [1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000]

medias1 = [436, 1163, 1237,1985, 2267, 3176,2975, 4180 , 4905, 6799]

medias2 = [24, 23, 29, 31, 31, 30, 29, 33, 34, 33]

medias3 = [16, 20, 21, 19, 21, 23, 22, 22, 21, 21]

plt.plot(n, medias)
plt.plot(n, medias1)
plt.plot(n, medias2)
plt.plot(n, medias3)
plt.show()
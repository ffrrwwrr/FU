import numpy

X = numpy.array([
    [0.2, 0, 0, 0, 0, 0.43, 0.37, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0.36, 0.64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0.08, 0.12, 0.53, 0, 0.27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0.53, 0, 0.47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0.21, 0.05, 0.28, 0, 0.11, 0.1, 0, 0.25, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0.26, 0.4, 0, 0, 0.13, 0, 0.2, 0, 0.01, 0, 0, 0, 0, 0, 0, 0],
    [0, 0.33, 0.3, 0, 0, 0, 0.14, 0, 0.23, 0, 0, 0, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0.38, 0.12, 0.08, 0.28, 0, 0, 0.14, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0.32, 0, 0, 0, 0.08, 0, 0.16, 0.44, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0.46, 0, 0, 0.01, 0.06, 0, 0, 0.47, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0.01, 0.08, 0.19, 0, 0, 0.11, 0.24, 0.18, 0.19],
    [0, 0, 0, 0, 0, 0, 0, 0, 0.47, 0, 0.08, 0.06, 0, 0, 0.01, 0.38, 0],
    [0, 0, 0, 0, 0, 0, 0, 0.18, 0.05, 0, 0, 0.16, 0.08, 0.28, 0.04, 0, 0.21],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.5, 0, 0.36, 0.14, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.81, 0.19, 0, 0],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2, 0, 0.05, 0, 0.09, 0.48, 0.18],
    [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.34, 0.45, 0.05, 0, 0, 0.11, 0.05],
])

#1) вероятность того, что за 8 шагов система перейдет из состояния 14 в
#состояние 9;
k, i, j= 8, 14, 9

result = numpy.copy(X)
for _ in range(k-1):
    result = numpy.dot(result, X)

print(result[i - 1][j - 1])

# 2) вероятности состояний системы спустя 10 шагов, если в начальный
# момент вероятность состояний были следующими
# A=(0,06;0,06;0,09;0,06;0,08;0,04;0,01;0,1;0,07;0,08;0,01;0,04;0,1;0,1;0,05;0,04;0,01);
A = numpy.array([[0.06,0.06,0.09,0.06,0.08,0.04,0.01,0.1,0.07,0.08,0.01,0.04,0.1,0.1,0.05,0.04,0.01]])

k = 10
result = numpy.copy(A)
for i in range(k):
    result = numpy.dot(result, X)

print(A)

#3) вероятность первого перехода за 8 шагов из состояния 6 в состояние 15;
k, i, j = 8, 6, 15

result = numpy.copy(X)
for l in range(1, k):
    temp = numpy.zeros((len(X), len(X)))
    for n in range(len(X)):
        for l in range(len(X)):
            for m in range(len(X)):
                if m != j:
                    temp[n, l] += X[n, m] * result[m, l]
    result = temp

print(result[i - 1][j - 1])

#4) вероятность перехода из состояния 10 в состояние 8 не позднее чем за 10
#шагов;
k, i, j = 10, 10, 8

result = numpy.copy(X)
answer = result[i - 1][j - 1]
for l in range(1, k):
    temp = numpy.zeros((len(X), len(X)))
    for n in range(len(X)):
        for l in range(len(X)):
            for m in range(len(X)):
                if m != j:
                    temp[n, l] += X[n, m] * result[m, l]
    result = temp
    answer += result[i - 1][j - 1]
print(answer)
    
#5) среднее количество шагов для перехода из состояния 8 в состояние 10;
i, j = 8, 10
result = numpy.copy(X)
answer = result[i - 1][j - 1]
for l in range(1, 500):
    temp = numpy.zeros((len(X), len(X)))
    for n in range(len(X)):
        for l in range(len(X)):
            for m in range(len(X)):
                if m != j:
                    temp[n, l] += X[n, m] * result[m, l]
    result = temp
    answer += (l + 1) * result[i - 1][j - 1]
print(answer)

#6) вероятность первого возвращения в состояние 9 за 9 шагов;
k, i = 9, 9


def task6(k):
    result = numpy.linalg.matrix_power(X, k)
    if k == 1:
        return result
    summ = 0
    for m in range(1, k):
        summ += task6(m) * numpy.linalg.matrix_power(X, k - m)
    return result - summ

res = task6(k)
print(res[i - 1][i - 1])

#7) вероятность возвращения в состояние 14 не позднее чем за 9 шагов;
k, i = 9, 14

ans = 0
for m in range(1, k + 1):
    ans += task6(m)[i - 1][i - 1]
print(ans)

#8) среднее время возвращения в состояние 5;
i, k = 5, 20

ans = 0
for m in range(1, k + 1):
    ans += m * task6(m)[i - 1][i - 1]
print(ans)

#
X_transpose = numpy.copy(X).transpose()
for i in range(len(X_transpose)):
    X_transpose[i][i] -= 1
X_transpose[-1, :] = 1

Y = numpy.zeros(len(X))
Y[-1] = 1
res = numpy.dot(numpy.linalg.inv(X_transpose), Y)

print(res)

l = 18
m = 6
mu = 3
n = 19

L = []
for i in range(m+n+1):
    temp = []
    for j in range(m+n+1):
        if j==i or abs(j-i) > 1:
            temp.append(0)
        elif i > j:
            temp.append(mu*min([m,i]))
        else:
            temp.append(l)
    L.append(temp)
L = numpy.array(L)
print(L)

#a) найдите установившиеся вероятности состояний
L_transpose = numpy.transpose(L)
D = numpy.zeros((m+n+1, m+n+1))
for i in range(m+n+1):
    for j in range(m+n+1):
        if i == j:
            D[i][j] = sum(L[i])
M = L_transpose - D
Y = numpy.zeros(n+m+1)
Y[m+n] = 1
M_ = M.copy()
for j in range(n+m+1):
    M_[n+m][j] = 1

res = numpy.dot(numpy.linalg.inv(M_), Y)
print(res)
print(sum(res))

#b) Найдите вероятность отказа в обслуживании.
print(res[-1])

#c) Найдите относительную и абсолютную интенсивность обслуживания
temp = 1 - res[-1]
print(temp)
print(l * temp)

#d) Найдите среднюю длину в очереди
mean = 0
for i in range(n):
    mean += i * res[m + i + 1]

print(mean)

#e) Найдите среднее время в очереди.
mean = 0
for i in range(n):
    mean += res[m + i] * (i + 1) / (m * mu)

print(mean)

#f) Найдите среднее число занятых каналов.

mean = 0
for i in range(1, m + n + 1):
    if i <= m:
        mean += i * res[i]
    else:
        mean += m * res[i]

print(mean)

#g) Найдите вероятность того, что поступающая заявка не будет ждать в очереди

print(sum(res[:m]))

#h) Найти среднее время простоя системы массового обслуживания

print((1 / numpy.sum(L, -1))[0])
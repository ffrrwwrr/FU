#!/usr/bin/env python
# -*- coding: utf-8 -*-

import socket

class MyExit(Exception):
    pass

sock = socket.socket()
server = input("Input server address:")
port = input("Input server port:")

if server == '':
    server = '127.0.0.1'
if port == '':
    port = 9090

try:
    sock.connect((server, int(port)))

    print('Server IP --> '+str(server)+' Port --> '+str(port))
    host = sock.getsockname()
    print('Client IP --> '+str(host[0])+' Port --> '+str(host[1]))


except ConnectionRefusedError as c:
    print(c)
    print("Connection refused")
    exit()


print("Input the data: ")

while True:

    try:
        promt=input()
    except KeyboardInterrupt as k:
        print(k)
        print("Stop program")
        exit()

    try:
        result=sock.send(promt.encode())
        if not result:
            raise Exception("There is no data!")
    except Exception as e:
        print(e)
        exit()

    try:
        data = sock.recv(1024).decode("utf8")
        if (len(data)==0):
            raise Exception("No data or loss of connection!")
        if ('exit' or 'sstop') in data.lower():
            raise MyExit("End of connection")

    except ConnectionResetError as e:
        print(e)
        print("Lost connection from server")
        sock.close()
        exit()

    except Exception as s:
        print(s)
        sock.close()
        exit()

    except MyExit as ex:
        print(ex)
        break
        exit()

    print(data)

sock.close()

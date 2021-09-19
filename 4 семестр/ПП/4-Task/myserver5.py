#!/usr/bin/env python
# -*- coding: utf-8 -*-

import socket

sock = socket.socket()
port = input("Input port:")
if port == '':
    port = 9090
sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
sock.bind(('', int(port)))

while True:
    print("Listening the port", int(port))
    sock.listen(1)

    try:
        conn, addr = sock.accept()
    except KeyboardInterrupt as k:
        print(k)
        print("Stop program")
        exit()

    print('Connection established:', addr[1])

    while True:

        try:
            data = conn.recv(1024).decode("utf8")
        except ConnectionResetError as e:
            print(e)
            print("Lost connection from client")
            exit()
        except KeyboardInterrupt as k:
            print(k)
            print("Stop program")
            print("Lost connection from client")
            exit()

        if not data:
            print("End communication")
            break

        if "stop" in data:
           break
        conn.send(data.upper().encode())
    
    if "stop" in data:
        break

conn.close()

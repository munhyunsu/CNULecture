#!/usr/bin/python
# -*- coding: utf-8 -*-

import socket



##### start main #####
def main():
	serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

	serverSocket.bind(('', 8888))

	serverSocket.listen(1)

	while(True):
		(conn, addr) = serverSocket.accept()
		print 'Connected with ' + addr[0] + ':' + str(addr[1])

		recvData = conn.recv(1024)
		fileName = recvData.strip() + '.2' # 수정 필요
		print 'fineName:\t', fileName
		recvData = conn.recv(1024)
		finishCode = recvData.strip()
		print 'finishCode:\t', finishCode

		saveFile = open(fileName, 'wb')
		finishCount = 0
		while(True):
			if(finishCount > 4):
				break
			recvData = conn.recv(1024)
			if(len(recvData) == 0):
				break
			if(recvData == finishCode):
				print 'finishCount'
				finishCount = finishCount + 1
			else:
				print 'writeFile'
				saveFile.write(recvData)
		
		saveFile.close()
		conn.close()
		print 'Complete Send'
##### end main #####




if __name__ == '__main__':
	main()





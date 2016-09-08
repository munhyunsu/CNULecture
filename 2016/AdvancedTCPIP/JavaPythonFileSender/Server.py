#!/usr/bin/python
# -*- coding: utf-8 -*-

import socket



##### start main #####
def main():
	'''
	8888번 포트로 들어오는 연결을 대기
	연결이 될 경우
	1. 파일 이름
	2. 종료 코드
	3. 파일 데이터
	순으로 전송받음
	종료코드가 3번 연속으로 왔을 경우에 파일 전송이 끝난 것으로 판단
	'''
	# TCP 소켓 생성
	# 주소, 포트에 이미 바인드가 되어있어도 생성할 수 있도록 옵션을 줌
	serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
	# Anywhere, 포트로 Bind
	serverSocket.bind(('', 8888))
	# 1번에 1명만 서비스
	serverSocket.listen(1)
	# 데몬은 계속 켜져 있음
	while(True):
		# 클라이언트의 연결을 대기
		# 연결이 될 경우 connection 파일이 반환됨
		(conn, addr) = serverSocket.accept()
		print 'Connected with ' + addr[0] + ':' + str(addr[1])
		# 1. 파일 이름 전송 받음
		recvData = conn.recv(1024)
		fileName = recvData.strip() + '.2' # 디버그용으로 .2 붙여둠 실사용시 수정 필요
		print 'fineName:\t', fileName
		# 2. 종료 코드 전송 받음
		recvData = conn.recv(1024)
		finishCode = recvData.strip() # 일부러 str 형식으로 저장, 추후 비교가 편리
		print 'finishCode:\t', finishCode
		# 저장할 파일 오픈
		saveFile = open(fileName, 'wb')
		# 종료 코드 읽은 회수 0으로 세팅
		finishCount = 0
		# 종료 코드가 연속 3번 들어올 때 까지 진행
		# 소켓에서 읽은 데이터가 0일 경우 종료
		while(True):
			# 종료 코드가 연속 3번 들어왔을 경우
			if(finishCount > 4):
				break
			# 소켓에서 데이터 읽음
			recvData = conn.recv(1024)
			# 소켓에서 읽은 데이터가 0 일경우 종료
			if(len(recvData) == 0):
				break
			# 종료 코드와 같을 경우 카운트 증가
			if(recvData == finishCode):
				print 'finishCount'
				finishCount = finishCount + 1
			# 종료 코드가 아닐 경우 파일에 쓰기
			else:
				print 'writeFile'
				finishCount = 0
				saveFile.write(recvData)
		# 오픈한 파일 닫기
		# 연결된 TCP 세션 닫기
		saveFile.close()
		conn.close()
		print 'Complete Send'
##### end main #####




if __name__ == '__main__':
	main()





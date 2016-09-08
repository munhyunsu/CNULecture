# Advanced Operating Systems

## 2016. 09. 07. 인트로덕션

### 학부 운영체제 필요
- 학부 운영체제 기반이 없을 경우 조금 힘듦
  - 안 배웠을 경우 선수 학습 혹은 더 많은 노력이 필요

### 강의 목적
- 학부 운영체제에서 배운 개념들이 리눅스 커널에 어떻게 구현되어있는지 배움

### 필요한 것
- 시험의 비중이 큼(80%)
- 텀 프로젝트 / 텀 페이퍼가 있음
- 수업에 들어오기 전 강의 노트를 읽어야 함
  - 진도 속도가 빠르기 때문에 따라가기가 함듦

### 리눅스
- Unix-like Operating systems
  - '빠른 커널을 만들자!'가 목적
- The kernel
  - Core part of the OS that resides in main memory
  - Main 메모리에 상주하며 주요 기능을 하는 것
  - Monolithic kernel
    - cf. Micro kernel
      - 기존 커널이 가지고 있던 기능을 일반 응용 프로그램으로 꺼냄
      - 수정이 쉬움: 커널 외부에 있는 기능을 바꿨을 경우 프로세스만 재시작하면 됨
    - 기능을 조금만 바꿔도 전채 소스를 다시 컴파일해야 함
      - Micro kernel에 비교해서 수정이 어렵기 때문에 Module을 사용
  - kernel thread를 사용
    - 운영체제가 해야하는 일 중 급하지 않은 것을 thread가 처리하도록 냅둠
      - Process context switch보다 Thread context switch가 더 빠름
- The kernel versions
  - 2.6: NPTL, in-kernel preemption 추가
    - 이 버전을 기준으로 공부를 함
    - system call을 수행하고 있을 때도 preemption을 수행
  - 이후에는 추가된 기능이 뚜렷하지 않음
  - v2.5 전까지는 3자리의 버전 번호를 사용: 이후에는 2자리
  - 앞 번호는 버전이 바뀔 정도의 큰 변화가 나타났을 때 바꿈
    - 정도에 따라서 둘째, 셋째 번호를 바꿈

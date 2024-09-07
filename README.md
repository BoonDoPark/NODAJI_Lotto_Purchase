# 프로젝트 소개
동행복권을 모티브로 노다지라는 도메인에 복권, 연금 복권, 스포츠 토토를 서비스하는 웹 애플리케이션을 구현하였습니다.

# 담당 업무
노다지에서 복권의 구매 및 당첨 서비스를 구현하였습니다.

# 기술 스택
- Sever
  
![FastAPI](https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi)
![Spring-Boot](https://img.shields.io/badge/spring--boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
- Infra
  
![Google Cloud](https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white)
![Kubernetes](https://img.shields.io/badge/kubernetes-%23326ce5.svg?style=for-the-badge&logo=kubernetes&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white)

- CI/CD
  
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

- Database
  
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

# ERD
![image](https://github.com/user-attachments/assets/420733a3-88b1-4c1a-ba85-42580bbe2e43)


# Data Flow
![image](https://github.com/user-attachments/assets/163457a3-7bde-4ba1-8459-38b6ce385c67)


# Trouble Shooting
로또의 등수별 포인트 지급 방식은 5등과 4등의 당첨자들 먼저 금액을 지급하고, 남은 총 금액의 75%는 1등 당첨자들에게 지급하고, 2등은 13%, 3등은 12%를 지급하는 방식이여서, 그 로직을 구현하는 방법이 매우 힘들었습니다.
제가 생각한 방법은 테이블을 LottoRankPoint라는 테이블을 생성하여 저장한 다음에 등수에 따른 포인트 지급 및 등수를 타 서버로 전달하는 방법을 구현하였습니다.
LottoResultServiceImpl의 메소드 하나의 로직이 200줄이 넘는 문제가 발생하였습니다.
로직의 결합도를 줄이기 위해서, 그 기능에 맞는 파일을 생성하여 로직을 분리하여 진행하므로써 결합도를 줄였습니다.

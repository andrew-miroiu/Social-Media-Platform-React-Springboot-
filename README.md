# OnlyFriends — Social Media Platform

A full-stack social media platform built with React, Spring Boot, and Supabase. Features real-time messaging, media sharing, and social interactions.

🔗 **Live Demo:** [onlyfriendsjavaedition.netlify.app](https://onlyfriendsjavaedition.netlify.app)

---

## Features

- **Authentication** — JWT-based auth + Google OAuth (sign in with Google)
- **Posts** — Create, like, and comment on photo and video posts
- **Social Graph** — Follow / unfollow users, personalized feed
- **Real-time Messaging** — Live one-on-one chat between users
- **Profiles** — Customizable user profiles with profile pictures
- **Media Uploads** — Photo and video upload support
- **Exception Handling** — Global exception handler with structured error responses
- **Unit Tests** — Service layer tested with JUnit 5 and Mockito
- **CI/CD Pipeline** — GitHub Actions runs tests on every push and auto-deploys to Render

---

## Tech Stack

### Frontend
| Technology | Purpose |
|---|---|
| React + TypeScript | UI framework |
| Tailwind CSS | Styling |
| Vite | Build tool |
| Supabase Realtime | Real-time messaging |

### Backend
| Technology | Purpose |
|---|---|
| Spring Boot 3 | REST API framework |
| Spring Security + JWT | Authentication & authorization |
| Maven | Dependency management |
| JUnit 5 + Mockito | Unit testing |

### Infrastructure
| Technology | Purpose |
|---|---|
| Supabase | PostgreSQL database + file storage |
| Docker | Containerization (multi-stage build) |
| Docker Compose | Local multi-service orchestration |
| GitHub Actions | CI/CD pipeline |
| Render | Backend deployment |
| Netlify | Frontend deployment |

---

## Architecture

```
┌─────────────────────────────────────────────────┐
│                   Client (React)                 │
│         Netlify · TypeScript · Tailwind          │
└────────────────────┬────────────────────────────┘
                     │ REST API + JWT
┌────────────────────▼────────────────────────────┐
│              Backend (Spring Boot)               │
│         Render · Docker · GitHub Actions         │
└──────┬───────────────────────┬──────────────────┘
       │                       │
┌──────▼──────┐     ┌──────────▼──────────┐
│  Supabase   │     │   Supabase Storage  │
│ PostgreSQL  │     │   (photos, videos)  │
└─────────────┘     └─────────────────────┘
```

---

## CI/CD Pipeline

Every push to `main` triggers the GitHub Actions pipeline:

```
push to main
     ↓
[CI] Run JUnit tests (mvn test)
     ↓ if tests pass
[CD] Deploy to Render via Deploy Hook
```

---

## Running Locally

### Prerequisites
- Java 23
- Node.js 20+
- Maven
- Docker (optional)

### Backend

```bash
cd springboot-todo-api
# Create application.properties with your environment variables
mvn spring-boot:run
```

### Frontend

```bash
cd Social-Media-App-TypeScript/client
npm install
npm run dev
```

### With Docker Compose

```bash
docker compose up
```

Starts both frontend and backend with a single command.

---

## Environment Variables

### Backend (`application.properties`)
```
SUPABASE_URL=
SUPABASE_KEY=
JWT_SECRET=
```

### Frontend (`.env`)
```
VITE_SUPABASE_URL=
VITE_SUPABASE_ANON_KEY=
VITE_API_URL=
```

---

## Project Structure

```
Social-Media-Platform/
├── Social-Media-App-TypeScript/   # React frontend
│   ├── client/
│   │   ├── src/
│   │   │   ├── components/
│   │   │   ├── pages/
│   │   │   └── lib/
│   │   └── Dockerfile
├── springboot-todo-api/           # Spring Boot backend
│   ├── src/
│   │   ├── main/java/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   └── security/
│   │   └── test/
│   └── Dockerfile
├── docker-compose.yml
└── .github/
    └── workflows/
        └── ci.yml
```

---

## Author

**Andrei Miroiu**
- GitHub: [@andrew-miroiu](https://github.com/andrew-miroiu)
- Email: andrew.miroiu@gmail.com

# 🚚 SmartFleet - Microservices Based Fleet Management System

SmartFleet is a scalable, production-grade fleet management system built using modern technologies, microservices, and best engineering practices. It is designed for real-time tracking, analytics, and centralized control of vehicles and trip data.

---

## 🧩 Core Modules

- **Vehicle Service** – Handles registration, update, and real-time status of vehicles
- **Tracking Service** – Manages live GPS tracking data using Kafka
- **Trip Service** – Manages trip creation, status, and history
- **Dashboard Service** – Aggregates data from all services for analytics
- **API Gateway** – Central entry point for UI/backend routing
- **Service Registry** – Eureka for service discovery

---

## 💻 Frontend (React)

A professional, mobile-responsive React dashboard that includes:
- Navbar with routes (Dashboard, Vehicles, Trips, Stats)
- Stats cards (Active, Idle, Trips)
- Recharts-based analytics (Activity charts)
- React Router and modular components
- TailwindCSS for UI styling

---

## ⚙️ Technologies Used

| Category | Stack |
|---------|--------|
| Backend | Spring Boot, Spring WebFlux (Reactive), Spring Cloud |
| Frontend | React, TailwindCSS, Recharts |
| Messaging | Apache Kafka |
| Service Discovery | Eureka |
| API Gateway | Spring Cloud Gateway |
| Containerization | Docker, Kubernetes (K8s) ready |
| CI/CD | Jenkins |
| Monitoring | Prometheus + Grafana |
| Database | PostgreSQL / MongoDB (per service) |

---

## 🌐 System Architecture

Each microservice runs independently and communicates over REST or Kafka events. All are discoverable via Eureka. The Gateway routes requests, and circuit breakers ensure fault tolerance.

---

## 🚀 Key Features

### ✅ Reactive Programming (WebFlux)
- All backend services are non-blocking, using `Mono`, `Flux`, and reactive DB clients for scalability.

### ✅ Kafka-based Event Streaming
- Trip start, vehicle status, and tracking events are published and consumed using Kafka.

### ✅ Design Patterns Implemented
- **Builder** for object construction (DTOs, entity)
- **Factory** for Kafka event producers
- **Observer** for event listeners
- **Singleton** for shared utilities

### ✅ Exception Handling
- Global `@ControllerAdvice` with custom `ApiException`, `ErrorResponse`
- Per-service error codes

### ✅ Logging & Monitoring
- Centralized logging using `logback` + MDC
- Prometheus metrics + Grafana dashboards

### ✅ Circuit Breaker (Resilience4j)
- Configured fallback for downstream service failures
- Metrics for open/half-open states

### ✅ Multi-threading
- Custom thread pools for async Kafka processing
- `CompletableFuture`/`Schedulers.parallel()` used in reactive chains

### ✅ Streams & Graph Algorithms
- Backend uses Java Streams for aggregation, sorting, filtering
- Graph algorithms for route optimization and vehicle clustering (future scope)

---

## 📦 Project Structure

```
smartfleet/
│
├── vehicle-service/
├── tracking-service/
├── trip-service/
├── dashboard-service/
├── api-gateway/
├── service-registry/
└── frontend/ (React UI)
```

---

## 🧪 Testing End-to-End

Each microservice has:
- REST endpoints (GET/POST/PUT/DELETE)
- Kafka listeners and producers
- API integration tests using JUnit + Mockito
- Manual testing via Postman + React UI

---

## 📈 Future Scope

- Role-based login/auth (JWT)
- Admin dashboard with alerts
- Geo-fencing using map APIs
- ML-driven trip prediction

---

Made with ❤️ for real-time fleet management by system design warriors.




# 🚀 SmartFleet Microservices Setup — Full Action Log

## 🛠️ Initial Project Setup
- Created Multi-Module Maven project:
    - Modules: `vehicle-service`, `tracking-service`, `dashboard-service`, `gateway-service`, `service-registry`
    - Each with `pom.xml`, `server.port`, `spring.application.name`, `/health` endpoint
- Verified services run with `mvn spring-boot:run`

---

## 🐳 Docker Setup
- Installed Docker Desktop (Windows)
- Pulled base image `openjdk:21-jdk-slim` (initial DNS error fixed)
- Wrote `Dockerfile` for each service
- Manually tested `docker build`, `docker run`

---

## 🧪 Kubernetes Setup (Local)
- Created Deployment YAMLs for all services
- Added readiness probes with `/actuator/health`
- YAMLs stored inside each service’s `/deployment` folder
- Initially missing K8s `Service` object caused DNS error
- Created `Service` YAML for `service-registry`:
```yaml
kind: Service
metadata:
  name: service-registry
spec:
  selector:
    app: service-registry
  ports:
    - port: 8761
      targetPort: 8761
```

---

## 🔐 DockerHub Integration
- Created DockerHub Personal Access Token
- Added Jenkins credentials `dockerhub-creds` (username + token)
- Updated Jenkinsfile:
    - `credentials('dockerhub-creds')`
    - Docker login with token
    - Tag & push all services

---

## 🔄 GitHub Integration + Jenkins
- Installed Jenkins & plugins
- Webhook set using `ngrok`
- Created `smartFleetPipeline` job
- Fixed webhook failure by updating `ngrok` URL
- Connected GitHub repo to Jenkins via PAT

---

## ⚙️ Jenkinsfile Refactoring
- Fixed image tag: `%DOCKERHUB_USERNAME%/...` to `%DOCKERHUB_CREDENTIALS_USR%/...`
- Corrected YAML paths in `kubectl apply`
- Stored kubeconfig in Jenkins and referenced in env

---

## 🧠 Eureka Fixes
- Services initially pointed to wrong `gateway-service:8761`
- Fixed to point to correct Eureka:
```properties
eureka.client.service-url.defaultZone=http://service-registry:8761/eureka
```
- Created `Service` for Eureka for DNS resolution

---

## ✅ Final Pipeline Flow Working
- Build → Dockerize → Push → Deploy via K8s
- Health check added via readinessProbe
- Jenkins pipeline succeeded on 14th run 🎉

---

## 🧾 Summary of Learnings

| Area        | Insight |
|-------------|---------|
| Docker      | Token login is more secure |
| Jenkins     | Match Jenkinsfile paths accurately |
| Kubernetes  | DNS works only with `Service` exposure |
| Eureka      | All services must point to Eureka, not gateway |
| Debugging   | Logs + isolation = solution |
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
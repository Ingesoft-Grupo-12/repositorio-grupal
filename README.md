# Universidad Nacional de Colombia - Sede Bogotá  
## Facultad de Ingeniería  
### Departamento de Sistemas e Industrial  

**Curso:** Ingeniería de Software 1 (2016701)  

---

## **Repositorio grupal - Ingeniería de Software 1 - 2024-2 Grupo 12**  
Este repositorio contiene la información, tareas, documentación y desarrollo del curso **Ingeniería de Software 1**. Está estructurado para facilitar la organización, colaboración y seguimiento de los contenidos y entregables a lo largo del semestre.  

---

## **Integrantes**
* Daniel Alejandro Duitama Correa
*  Edwin Felipe Pinilla Peralta
*  Miguel Angel Martinez Fernandez
*  Juan Sebastian Umaña Camacho


---

## **Proyecto: Sistema de mensajeria UNión**
Una plataforma de mensajería en tiempo real diseñada para facilitar la comunicación segura entre estudiantes y profesores, limitada al contexto de los cursos que imparten los profesores o a los que están inscritos los estudiantes.

![logo](logo.jpg)

---

## **Tecnologías utilizadas**
* **Backend:** Java, Spring Boot, WebSockets
* **Frontend:** React, Redux, WebSockets
* **Base de Datos:** MySQL
* **Autenticación:** OAuth2 (Google)
* **Despliegue:** AWS

---
## **gitignore**
```plaintext
### Java ###
# Compiled class file
*.class

# Log file
*.log

# BlueJ files
*.ctxt

# Mobile Tools for Java (J2ME)
.mtj.tmp/

# Package Files #
*.jar
*.war
*.nar
*.ear
*.zip
*.tar.gz
*.rar

# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*
replay_pid*

### react ###
.DS_*
logs
**/*.backup.*
**/*.back.*

node_modules
bower_components

*.sublime*

psd
thumb
sketch

# End of https://www.toptal.com/developers/gitignore/api/java,react
---
```
## **Estructura del Repositorio**  
La estructura inicial del repositorio es la siguiente (puede evolucionar con el tiempo según las necesidades del curso):  

```plaintext
.
├── .gitignore
├── README.md
├── Documentación
│   ├── Historias_de_Usuario
│   │   ├── historia_<nickname>_01.pdf
│   │   ├── historia_<nickname>_02.pdf
│   │   └── ... (más historias)
│   ├── Proyecto
│   │   ├── diagramas.md
│   │   ├── script_implementacion.sql
│   │   └── ... (más documentos relacionados al proyecto)
│   └── ... (otros documentos si es necesario, separado en carpetas)
├── Asignaciones
│   ├── Tarea_01_<nickname>.pdf
│   ├── Tarea_02.pdf
│   ├── Taller_<name>.pdf
│   └── ... (más archivos según se asignen a lo largo del curso)
└── Proyecto
    └── ... (estructura libre según la tecnología utilizada, revisar arquitecturas limpias)

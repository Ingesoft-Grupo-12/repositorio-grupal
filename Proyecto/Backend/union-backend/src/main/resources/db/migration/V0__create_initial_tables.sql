-- =========================
-- 1) Eliminar tablas
-- =========================
DROP TABLE IF EXISTS friendrequests;
DROP TABLE IF EXISTS courserequests;
DROP TABLE IF EXISTS announcements;
DROP TABLE IF EXISTS enrollments;
DROP TABLE IF EXISTS reviews;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS chatparticipants;
DROP TABLE IF EXISTS chats;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;

-- =========================
-- 2) Crear tablas
-- =========================

-- Tabla principal de usuarios
CREATE TABLE users (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name TEXT NOT NULL,
  email TEXT NOT NULL UNIQUE,
  role TEXT NOT NULL CHECK (role IN ('student','teacher','admin')),
  profile_picture TEXT
);

-- Tabla de chats
CREATE TABLE chats (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name TEXT,
  created_at TIMESTAMPTZ DEFAULT NOW()
);

-- Tabla que relaciona chats con usuarios (muchos-a-muchos)
CREATE TABLE chatparticipants (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  chat_id BIGINT NOT NULL REFERENCES chats (id),
  user_id BIGINT NOT NULL REFERENCES users (id)
);

-- Mensajes en los chats
CREATE TABLE messages (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  sender_id BIGINT NOT NULL REFERENCES users (id),
  content TEXT NOT NULL,
  "timestamp" TIMESTAMPTZ DEFAULT NOW(),
  chat_id BIGINT NOT NULL REFERENCES chats (id)
);

-- Cursos, cada curso asociado a un profesor (teacher_id)
CREATE TABLE courses (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name TEXT NOT NULL,
  description TEXT,
  teacher_id BIGINT NOT NULL REFERENCES users (id)
);

-- Inscripciones (muchos-a-muchos) entre cursos y usuarios (estudiantes)
CREATE TABLE enrollments (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  course_id BIGINT NOT NULL REFERENCES courses (id),
  student_id BIGINT NOT NULL REFERENCES users (id)
);

-- Anuncios en un curso
CREATE TABLE announcements (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  course_id BIGINT NOT NULL REFERENCES courses (id),
  content TEXT NOT NULL,
  "timestamp" TIMESTAMPTZ DEFAULT NOW()
);

-- Peticiones de amistad entre usuarios
CREATE TABLE friendrequests (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  sender_id BIGINT NOT NULL REFERENCES users (id),
  receiver_id BIGINT NOT NULL REFERENCES users (id),
  status TEXT CHECK (status IN ('pending','accepted','rejected')) DEFAULT 'pending',
  request_date TIMESTAMPTZ DEFAULT NOW()
);

-- Peticiones de curso (por parte de estudiantes)
CREATE TABLE courserequests (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  course_id BIGINT NOT NULL REFERENCES courses (id),
  student_id BIGINT NOT NULL REFERENCES users (id),
  status TEXT CHECK (status IN ('pending','accepted','rejected')) DEFAULT 'pending',
  request_date TIMESTAMPTZ DEFAULT NOW()
);

-- Reseñas de cursos (y su profesor)
CREATE TABLE reviews (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  course_id BIGINT NOT NULL REFERENCES courses (id),
  teacher_id BIGINT NOT NULL REFERENCES users (id),
  rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  comment TEXT,
  created_at TIMESTAMPTZ DEFAULT NOW()
);

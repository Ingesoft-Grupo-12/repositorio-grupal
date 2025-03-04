"use client";

import { useState, useEffect } from "react";
import ReactPortal from "@/components/shared/ReactPortal";
import { FiSearch } from "react-icons/fi";
import UserCard from "@/components/chats/UserCard/UserCard";
import { UserType } from "@/app/chats/chatsTypings";

type NewCourseModalProps = {
  isOpen: boolean;
  handleClose: () => void;
};

export default function NewCourseModal({
  isOpen,
  handleClose,
}: NewCourseModalProps) {
  const [courseName, setCourseName] = useState("");
  const [courseDescription, setCourseDescription] = useState("");
  const [searchQuery, setSearchQuery] = useState("");
  const [tempSearchQuery, setTempSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState<UserType[]>([]);
  const [selectedUsers, setSelectedUsers] = useState<UserType[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    const debounceTimeout = setTimeout(() => {
      setSearchQuery(tempSearchQuery);
    }, 500);
    return () => clearTimeout(debounceTimeout);
  }, [tempSearchQuery]);

  useEffect(() => {
    if (!searchQuery) {
      setSearchResults([]);
      return;
    }

    setLoading(true);
    fetch(`/api/users`)
      // fetch(`/api/users?query=${searchQuery}`)
      .then((res) => res.json())
      .then((data) => {
        setSearchResults(data);
      })
      .catch((err) => console.error("Error fetching users:", err))
      .finally(() => setLoading(false));
  }, [searchQuery]);

  const validateCourse = () => {
    if (courseName.trim() === "") {
      setError("El curso debe tener un nombre.");
      return false;
    }

    if (selectedUsers.length === 0) {
      setError("Debes seleccionar al menos un usuario.");
      return false;
    }

    setError("");
    return true;
  };

  const handleUserSelect = (user: UserType) => {
    if (!selectedUsers.some((u) => u.userId === user.userId)) {
      setSelectedUsers([...selectedUsers, user]);
    }
  };

  const handleUserRemove = (userId: number) => {
    setSelectedUsers(selectedUsers.filter((user) => user.userId !== userId));
  };

  const handleCreateCourse = () => {
    if (!validateCourse()) {
      return;
    }
    console.log("Creating course with:", {
      courseName,
      courseDescription,
      selectedUsers,
    });
    handleClose();
  };

  if (!isOpen) return null;

  return (
    <ReactPortal wrapperId="react-portal-modal-container">
      <div className="fixed top-0 left-0 w-screen h-screen z-40 flex items-center justify-center bg-black bg-opacity-50 text-black">
        <div className="bg-white rounded-lg shadow-lg w-full max-w-2xl p-6 lg:w-1/2">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-semibold">Crear Nuevo Curso</h2>
            <button
              onClick={handleClose}
              className="text-gray-500 hover:text-gray-700"
            >
              ✖
            </button>
          </div>

          <input
            type="text"
            placeholder="Nombre del curso"
            value={courseName}
            onChange={(e) => setCourseName(e.target.value)}
            className="w-full mb-3 p-2 border border-gray-300 rounded-lg"
          />

          <input
            type="text"
            placeholder="Descripción breve"
            value={courseDescription}
            onChange={(e) => setCourseDescription(e.target.value)}
            className="w-full mb-3 p-2 border border-gray-300 rounded-lg"
          />

          <div className="relative mb-4">
            <FiSearch className="absolute left-3 top-3 text-gray-400" />
            <input
              type="text"
              placeholder="Buscar usuarios..."
              value={tempSearchQuery}
              onChange={(e) => setTempSearchQuery(e.target.value)}
              className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div className="bg-gray-50 rounded-lg overflow-y-auto max-h-96">
            {loading && <p className="text-gray-500">Cargando...</p>}
            {!loading && searchResults.length === 0 && searchQuery && (
              <p className="text-gray-500">No se encontraron resultados.</p>
            )}
            {!loading && searchResults.length > 0 && (
              <div className="divide-y divide-gray-200">
                {searchResults.map((user) => (
                  <div
                    key={user.userId}
                    className="flex justify-between items-center p-2"
                  >
                    <UserCard {...user} />

                    <button
                      onClick={() => handleUserSelect(user)}
                      className="mr-4 text-blue-500"
                    >
                      Agregar
                    </button>
                  </div>
                ))}
              </div>
            )}
          </div>

          {selectedUsers.length > 0 && (
            <div className="mt-4 overflow-y-auto max-h-36">
              <h3 className="font-semibold">Usuarios seleccionados:</h3>
              <ul className="mt-2">
                {selectedUsers.map((user) => (
                  <li
                    key={user.userId}
                    className="flex justify-between items-center p-2 border-b"
                  >
                    {user.userName}
                    <button
                      onClick={() => handleUserRemove(user.userId)}
                      className="ml-2 text-red-500"
                    >
                      Eliminar
                    </button>
                  </li>
                ))}
              </ul>
            </div>
          )}
          {error && <p className="text-red-500 text-sm mt-2">{error}</p>}
          <button
            onClick={handleCreateCourse}
            className="mt-4 w-full bg-blue-500 text-white p-2 rounded-lg hover:bg-blue-600"
          >
            Crear Curso
          </button>
        </div>
      </div>
    </ReactPortal>
  );
}

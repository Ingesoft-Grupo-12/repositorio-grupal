"use client";

import { useState, useEffect } from "react";
import ReactPortal from "@/components/shared/ReactPortal";
import { FiSearch } from "react-icons/fi";
import AddFriendCard from "@/components/chats/FriendCard/AddFriendCard";
import { UserType } from "@/app/chats/chatsTypings";

type AddFriendsModalProps = {
  isOpen: boolean;
  handleClose: () => void;
};

export default function AddFriendsModal({
  isOpen,
  handleClose,
}: AddFriendsModalProps) {
  const [searchQuery, setSearchQuery] = useState("");
  const [tempSearchQuery, setTempSearchQuery] = useState("");
  const [searchResults, setSearchResults] = useState<UserType[]>([]);
  const [loading, setLoading] = useState(false);

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

  if (!isOpen) return null;

  return (
    <ReactPortal wrapperId="react-portal-modal-container">
      <div className="fixed top-0 left-0 w-screen h-screen z-40 flex items-center justify-center bg-black bg-opacity-50 text-black">
        <div className="bg-white rounded-lg shadow-lg w-full max-w-2xl p-6 lg:w-1/2">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-semibold">Añadir Amigos</h2>
            <button
              onClick={handleClose}
              className="text-gray-500 hover:text-gray-700"
            >
              ✖
            </button>
          </div>
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
          <div>
            {loading && <p className="text-gray-500">Cargando...</p>}
            {!loading && searchResults.length === 0 && searchQuery && (
              <p className="text-gray-500">No se encontraron resultados.</p>
            )}
            {!loading && searchResults.length > 0 && (
              <div className="divide-y divide-gray-200">
                {searchResults.map((user) => (
                  <div
                    key={user.id}
                    className="flex justify-between items-center p-2"
                  >
                    <AddFriendCard {...user} />
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      </div>
    </ReactPortal>
  );
}

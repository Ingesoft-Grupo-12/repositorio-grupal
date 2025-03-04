"use client";

import React, { useState, useEffect } from "react";
import ReactPortal from "@/components/shared/ReactPortal";
import { FiSearch } from "react-icons/fi";
import AddFriendCard from "@/components/chats/FriendCard/AddFriendCard";
import { UserType, ChatDataType } from "@/app/chats/chatsTypings";

type NewChatModalProps = {
  isOpen: boolean;
  setSelectedChat: (option: ChatDataType | null) => void;
  handleClose: () => void;
};

export default function NewChatModal({
  isOpen,
  setSelectedChat,
  handleClose,
}: NewChatModalProps) {
  const [friendsLoading, setFriendsLoading] = useState(false);
  const [friends, setFriends] = useState<UserType[]>([]);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    if (isOpen) fetchFriends();
  }, [isOpen]);

  if (!isOpen) return null;

  const fetchFriends = async () => {
    setFriendsLoading(true);
    try {
      const response = await fetch("/api/friends");
      if (!response.ok) throw new Error("Failed to fetch friends");
      const data: UserType[] = await response.json();
      setFriends(data);
    } catch (error) {
      console.error("Error fetching friends:", error);
    } finally {
      setFriendsLoading(false);
    }
  };

  const filteredFriends = friends.filter(
    (friend) =>
      friend.userName.toLowerCase().includes(searchQuery.toLowerCase()) ||
      friend.userEmail.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const handleSelectFriend = (friend: UserType) => {
    const chatData: ChatDataType = {
      userId: friend.userId,
      userImage: friend.userImage,
      userName: friend.userName,
      lastMessage: "",
      lastMessageTime: "",
    };

    setSelectedChat(chatData);
    handleClose();
  };

  return (
    <ReactPortal wrapperId="react-portal-modal-container">
      <div className="fixed top-0 left-0 w-screen h-screen z-40 flex items-center justify-center bg-black bg-opacity-50 text-black">
        <div className="bg-white rounded-lg shadow-lg w-full max-w-2xl p-6 lg:w-1/2">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-xl font-semibold">
              Selecciona a alguien para iniciar un nuevo chat
            </h2>
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
              placeholder="Buscar amigos..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
          </div>

          <div>
            {friendsLoading && (
              <p className="text-gray-500">Cargando amigos...</p>
            )}
            {!friendsLoading && filteredFriends.length === 0 && (
              <p className="text-gray-500">No se encontraron resultados.</p>
            )}
            {!friendsLoading && filteredFriends.length > 0 && (
              <div className="divide-y divide-gray-200">
                {filteredFriends.map((user) => (
                  <div
                    key={user.userId}
                    className="flex justify-between items-center p-2 cursor-pointer hover:bg-gray-100"
                    onClick={() => handleSelectFriend(user)}
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

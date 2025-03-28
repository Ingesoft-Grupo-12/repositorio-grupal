"use client";

import { useEffect, useState } from "react";
import ChatCard from "./ChatCard/ChatCard";
import FriendCard from "./FriendCard/FriendCard";
import SkeletonChatCard from "./ChatCard/SkeletonChatCard";
import { ChatDataType, FriendType, ModuleType } from "@/app/chats/chatsTypings";
import { FaPlus } from "react-icons/fa";
import { FiSearch } from "react-icons/fi";
import AddFriendModal from "./Modals/AddFriendsModal";

type ChatSidebarProps = {
  selectedModule: ModuleType;
  selectedChat: ChatDataType | null;
  selectedFriend: FriendType | null;
  setSelectedChat: (option: ChatDataType) => void;
  setSelectedFriend: (option: FriendType) => void;
};

export default function ChatSidebar({
  selectedModule,
  selectedChat,
  setSelectedChat,
  selectedFriend,
  setSelectedFriend,
}: ChatSidebarProps) {
  const [sidebarChats, setSidebarChats] = useState<ChatDataType[]>([]);
  const [messageLoading, setMessageLoading] = useState(false);
  const [sidebarFriends, setSidebarFriends] = useState<FriendType[]>([]);
  const [friendsLoading, setFriendsLoading] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");
  const [tempSearchQuery, setTempSearchQuery] = useState("");
  const [addFriendModalOpen, setAddFriendModalOpen] = useState(false);

  useEffect(() => {
    if (selectedModule === "messages") {
      fetchChats();
    } else if (selectedModule === "friends") {
      fetchFriends();
    }
  }, [selectedModule]);

  useEffect(() => {
    const debounceTimeout = setTimeout(() => {
      setSearchQuery(tempSearchQuery);
    }, 500);

    return () => clearTimeout(debounceTimeout);
  }, [tempSearchQuery]);

  const fetchChats = async () => {
    setMessageLoading(true);
    try {
      const response = await fetch("http://localhost:3000/api/chats");
      if (!response.ok) throw new Error("Failed to fetch chats");
      const data: ChatDataType[] = await response.json();
      setSidebarChats(data);
    } catch (error) {
      console.error("Error fetching chats:", error);
    } finally {
      setMessageLoading(false);
    }
  };

  const fetchFriends = async () => {
    setFriendsLoading(true);
    try {
      const response = await fetch("http://localhost:3000/api/friends");
      if (!response.ok) throw new Error("Failed to fetch friends");
      const data: FriendType[] = await response.json();
      setSidebarFriends(data);
    } catch (error) {
      console.error("Error fetching friends:", error);
    } finally {
      setFriendsLoading(false);
    }
  };

  const filteredChats = sidebarChats.filter((chat) =>
    chat.userName.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const filteredFriends = sidebarFriends.filter(
    (friend) =>
      friend.userName.toLowerCase().includes(searchQuery.toLowerCase()) ||
      friend.email.toLowerCase().includes(searchQuery.toLowerCase())
  );

  const renderModuleTitle = (module: ModuleType) => {
    if (module === "messages") {
      return "Mensajes";
    } else if (module === "friends") {
      return "Amigos";
    } else if (module === "transport") {
      return "Transporte";
    }
  };

  const renderChats = () => {
    if (messageLoading) return <SkeletonChatCard numberOfCards={10} />;
    if (filteredChats.length === 0 && !searchQuery)
      return (
        <div className="flex justify-center items-center h-full text-gray-500 text-center px-4">
          <p className="break-words max-w-full">
            No tienes chats activos, inicia uno :D
          </p>
        </div>
      );

    return filteredChats.map((chat) => {
      const isSelected = selectedChat?.userId === chat.userId;
      return (
        <div
          key={chat.userId}
          className={`cursor-pointer ${
            isSelected ? "bg-gray-100" : "hover:bg-gray-100"
          }`}
          onClick={() => setSelectedChat(chat)}
        >
          <ChatCard {...chat} />
        </div>
      );
    });
  };

  const renderFriends = () => {
    if (friendsLoading) return <SkeletonChatCard numberOfCards={10} />;
    if (filteredFriends.length === 0)
      return (
        <div className="flex justify-center items-center h-full text-gray-500 text-center px-4">
          <p className="break-words max-w-full">
            No tienes amigos aún, intenta agregar a alguien que conozcas
          </p>
        </div>
      );

    return filteredFriends.map((friend) => {
      const isSelected = selectedFriend?.userId === friend.userId;
      return (
        <div
          key={friend.userId}
          className={`cursor-pointer ${
            isSelected ? "bg-gray-100" : "hover:bg-gray-100"
          }`}
          onClick={() => setSelectedFriend(friend)}
        >
          <FriendCard {...friend} />
        </div>
      );
    });
  };

  const getPlaceholder = (module: ModuleType) => {
    if (module === "messages") return "Buscar chats...";
    if (module === "friends") return "Buscar amigos...";
    if (module === "transport") return "Buscar transportes...";
    return "Buscar...";
  };

  return (
    <div className="flex-grow ml-2 rounded-md min-w-72 w-full max-w-96 overflow-y-auto bg-white">
      <div className="p-4">
        <h1 className="text-2xl font-semibold mt-4">
          {renderModuleTitle(selectedModule)}
        </h1>
      </div>

      <div className="p-4 flex items-center gap-2">
        <div className="relative flex-grow">
          <input
            type="text"
            placeholder={getPlaceholder(selectedModule)}
            value={tempSearchQuery}
            onChange={(e) => setTempSearchQuery(e.target.value)}
            className="w-full pl-10 pr-4 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
          />
          <FiSearch className="absolute left-3 top-3 text-gray-400" />
        </div>
        <button
          className="p-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors"
          onClick={() => {
            if (selectedModule === "messages") {
              console.log("Crear nuevo chat");
            } else if (selectedModule === "friends") {
              setAddFriendModalOpen(!addFriendModalOpen);
            }
          }}
        >
          <FaPlus />
        </button>
        {addFriendModalOpen && (
          <AddFriendModal
            isOpen={addFriendModalOpen}
            handleClose={() => setAddFriendModalOpen(false)}
          />
        )}
      </div>

      <div className="overflow-y-auto">
        {selectedModule === "messages" && renderChats()}
        {selectedModule === "friends" && renderFriends()}
      </div>
    </div>
  );
}

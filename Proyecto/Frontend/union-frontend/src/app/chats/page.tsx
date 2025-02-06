"use client";

import { useState } from "react";
import UtilSidebar from "@/components/chats/UtilSidebar";
import ChatSidebar from "@/components/chats/ChatSidebar";
import ChatArea from "@/components/chats/ChatArea/ChatArea";
import { ChatDataType, FriendType, ModuleType } from "./chatsTypings";

export default function Chats() {
  const [selectedModule, setSelectedModule] = useState<ModuleType>("messages");
  const [selectedChat, setSelectedChat] = useState<ChatDataType | null>(null);
  const [selectedFriend, setSelectedFriend] = useState<FriendType | null>(null);

  return (
    <div className="overflow-auto h-screen flex p-4 bg-gray-200 text-black">
      <UtilSidebar
        selectedModule={selectedModule}
        setSelectedModule={setSelectedModule}
      />
      <ChatSidebar
        selectedModule={selectedModule}
        selectedChat={selectedChat}
        setSelectedChat={setSelectedChat}
        selectedFriend={selectedFriend}
        setSelectedFriend={setSelectedFriend}
      />
      <ChatArea selectedChat={selectedChat} />
    </div>
  );
}

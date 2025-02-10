"use client";

import { useEffect, useState } from "react";
import ChatHeader from "./ChatHeader";
import ChatBody from "./ChatBody";
import ChatBox from "./ChatBox";
import { MessageType, SelectedChatDataType } from "@/app/chats/chatsTypings";

type ChatAreaProps = {
  selectedChat: SelectedChatDataType | null;
};

export default function ChatArea({ selectedChat }: ChatAreaProps) {
  const [messages, setMessages] = useState<MessageType[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    if (!selectedChat) return;

    const fetchMessages = async () => {
      setLoading(true);
      try {
        const response = await fetch("http://localhost:3000/api/messages");
        if (!response.ok) throw new Error("Error al cargar mensajes");
        const data: MessageType[] = await response.json();
        setMessages(data);
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    fetchMessages();
  }, [selectedChat]);

  if (!selectedChat) {
    return (
      <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full items-center justify-center">
        <p className="text-gray-500">
          Selecciona un chat para comenzar la conversación
        </p>
      </div>
    );
  }

  return (
    <div className="flex flex-col ml-2 bg-white p-4 rounded-xl w-full min-w-96 h-full">
      <ChatHeader
        userName={selectedChat.userName}
        userImage={selectedChat.userImage}
      />

      {loading ? (
        <p className="text-gray-500 text-center">Cargando mensajes...</p>
      ) : (
        <ChatBody messages={messages} userId={selectedChat.userId} />
      )}

      <div className="mt-auto">
        <ChatBox />
      </div>
    </div>
  );
}

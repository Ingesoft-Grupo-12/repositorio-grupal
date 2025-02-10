"use client";

import MessageCard from "./MessageCard";
import { MessageType } from "@/app/chats/chatsTypings";

type ChatBodyProps = {
  messages: MessageType[];
  userId: number;
};

export default function ChatBody({ messages, userId }: ChatBodyProps) {
  return (
    <div className="flex flex-col justify-end bg-gray-200 p-4 rounded-md h-full">
      <div className="max-w-4xl w-full mx-auto overflow-y-auto">
        {messages.length === 0 ? (
          <p className="text-gray-500 text-center">No hay mensajes</p>
        ) : (
          messages.map((msg) => (
            <MessageCard
              key={msg.messageId}
              message={msg.content}
              time={msg.time}
              isCurrentUser={userId === msg.senderId}
            />
          ))
        )}
      </div>
    </div>
  );
}

"use client";

import MessageCard from "./ChatMessageCard";
import { MessageType } from "@/app/chats/chatsTypings";
import { format, isYesterday, isToday, isThisWeek } from "date-fns";
import { es } from "date-fns/locale";

type ChatBodyProps = {
  chatMessages: MessageType[];
  userId: number | null;
};

const getDateLabel = (date: Date) => {
  if (isToday(date)) return "Hoy";
  if (isYesterday(date)) return "Ayer";
  if (isThisWeek(date, { weekStartsOn: 1 })) {
    return format(date, "EEEE", { locale: es });
  }

  return format(date, "dd/MM/yyyy");
};

const formatTime = (date: Date) => format(date, "hh:mm a");

export default function ChatBody({ chatMessages, userId }: ChatBodyProps) {
  let lastDateLabel = "";

  return (
    <div className="flex flex-col justify-start bg-gray-200 p-4 rounded-md h-full">
      <div className="max-w-4xl w-full mx-auto overflow-y-auto">
        {chatMessages.length === 0 ? (
          <p className="text-gray-500 text-center">No hay mensajes</p>
        ) : (
          chatMessages.map((msg) => {
            const messageDate = new Date(msg.time);
            const dateLabel = getDateLabel(messageDate);
            const showDateLabel = dateLabel !== lastDateLabel;
            lastDateLabel = dateLabel;

            return (
              <div key={msg.messageId}>
                {showDateLabel && (
                  <div className="text-center text-gray-600 text-sm font-semibold my-2">
                    {dateLabel}
                  </div>
                )}
                <MessageCard
                  message={msg.content}
                  time={formatTime(messageDate)}
                  isCurrentUser={userId === msg.senderId}
                />
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}

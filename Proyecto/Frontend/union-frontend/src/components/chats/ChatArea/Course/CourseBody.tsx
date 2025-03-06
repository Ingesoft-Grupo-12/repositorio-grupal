"use client";

import CourseMessageCard from "../Course/CourseMessageCard";
import { CourseMessageType } from "@/app/chats/chatsTypings";
import { format, isYesterday, isToday, isThisWeek } from "date-fns";
import { es } from "date-fns/locale";

type CourseBodyProps = {
  courseMessages: CourseMessageType[];
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

export default function CourseBody({
  courseMessages,
  userId,
}: CourseBodyProps) {
  let lastDateLabel = "";
  let lastUserId: number | null = null;

  return (
    <div className="flex flex-col justify-start bg-gray-200  p-4 rounded-md h-full overflow-y-auto">
      <div className="max-w-4xl w-full mx-auto overflow-y-auto">
        {courseMessages.length === 0 ? (
          <p className="text-gray-500 text-center">No hay mensajes</p>
        ) : (
          courseMessages.map((msg) => {
            const messageDate = new Date(msg.time);
            const dateLabel = getDateLabel(messageDate);
            const showDateLabel = dateLabel !== lastDateLabel;
            const showUserInfo = lastUserId !== msg.user.userId;

            lastDateLabel = dateLabel;
            lastUserId = msg.user.userId;

            return (
              <div key={msg.messageId}>
                {showDateLabel && (
                  <div className="text-center text-gray-600 text-sm font-semibold my-2">
                    {dateLabel}
                  </div>
                )}
                <CourseMessageCard
                  user={msg.user}
                  message={msg.content}
                  time={formatTime(messageDate)}
                  isCurrentUser={userId === msg.user.userId}
                  showUserInfo={showUserInfo}
                />
              </div>
            );
          })
        )}
      </div>
    </div>
  );
}

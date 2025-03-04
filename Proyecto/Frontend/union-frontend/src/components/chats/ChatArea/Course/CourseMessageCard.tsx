"use client";

import Image from "next/image";
import { UserType } from "@/app/chats/chatsTypings";

type CourseMessageCardProps = {
  user: UserType;
  message: string;
  time: string;
  isCurrentUser: boolean;
  showUserInfo: boolean;
};

export default function CourseMessageCard({
  user,
  message,
  time,
  isCurrentUser,
  showUserInfo,
}: CourseMessageCardProps) {
  return (
    <div
      className={`flex ${isCurrentUser ? "justify-end" : "justify-start"} mb-1`}
    >
      <div className="w-10 h-10 mr-2 flex-shrink-0">
        {!isCurrentUser && showUserInfo && (
          <Image
            src={user.userImage}
            alt={user.userName}
            width={40}
            height={40}
            className="rounded-full"
          />
        )}
      </div>

      <div className="flex flex-col">
        <div
          className={`relative max-w-xs p-3 rounded-lg ${
            isCurrentUser
              ? "bg-green-200 text-black self-end"
              : "bg-gray-100 text-black"
          }`}
        >
          {!isCurrentUser && showUserInfo && (
            <span className="text-sm font-semibold">{user.userName}</span>
          )}
          <p>{message}</p>
          <span className="block text-xs text-gray-500 text-right mt-1">
            {time}
          </span>
        </div>
      </div>
    </div>
  );
}

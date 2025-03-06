"use client";

import React from "react";
import Image from "next/image";
import DefaultAvatar from "@/assets/images/default-avatar.svg";

type AddFriendChatCardProps = {
  id: number;
  userimage: string;
  username: string;
  email: string;
  role: string;
};

export default function FriendChatCard({
  userimage,
  username,
  email,
}: AddFriendChatCardProps) {
  return (
    <div className="flex w-full items-center justify-between p-4">
      <div className="flex items-center flex-grow min-w-0">
        <Image
          src={userimage || DefaultAvatar}
          width={48}
          height={48}
          alt={`${username}'s avatar`}
          className="rounded-full object-cover flex-shrink-0 overflow-hidden"
        />
        <div className="ml-4 flex-grow min-w-0">
          <div className="font-medium truncate">{username}</div>
          <div className="text-sm text-gray-400 truncate">{email}</div>
        </div>
      </div>
      <div className="ml-4 flex-shrink-0">
        {/* {(!requestStatus || requestStatus === "denied") && (
          <button
            className="bg-blue-500 text-white px-3 py-1 rounded-lg text-sm hover:bg-blue-600 transition-colors"
            onClick={() => console.log("API-Añadir Amigo", userId)}
          >
            Añadir amigo
          </button>
        )}
        {requestStatus === "accepted" && (
          <span className="text-green-500 text-sm">Amigos</span>
        )}
        {requestStatus === "pending" && (
          <span className="text-gray-500 text-sm">Pendiente</span>
        )} */}
      </div>
    </div>
  );
}

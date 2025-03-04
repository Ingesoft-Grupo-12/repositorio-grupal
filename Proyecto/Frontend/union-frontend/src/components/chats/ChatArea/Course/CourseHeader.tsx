"use client";

import Image from "next/image";

import { UserType } from "@/app/chats/chatsTypings";

type CourseHeaderProps = {
  courseName: string;
  courseImage: string;
  courseUsers: Array<UserType>;
};

export default function CourseHeader({
  courseName,
  courseImage,
  courseUsers,
}: CourseHeaderProps) {
  return (
    <div className="flex items-center bg-white p-4 shado">
      <Image
        src={courseImage}
        alt={courseName || "User avatar"}
        width={40}
        height={40}
        className="rounded-full"
      />
      <div className="flex flex-col ml-4 truncate">
        <span className="text-lg">{courseName}</span>
        <span className="text-sm text-gray-500 overflow-hidden whitespace-nowrap text-ellipsis max-w-full">
          {courseUsers.map((user) => user.userName).join(", ")}
        </span>
        {/* <span className="text-sm text-green-500">escribiendo..</span> */}
      </div>
    </div>
  );
}

"use client";

import Image from "next/image";

type CourseHeaderProps = {
  name: string;
  courseImage: string;
  description: string;
};

export default function CourseHeader({
  name,
  courseImage,
  description,
}: CourseHeaderProps) {
  return (
    <div className="flex items-center bg-white p-4 shado">
      <Image
        src={courseImage}
        alt={name || "User avatar"}
        width={40}
        height={40}
        className="rounded-full"
      />
      <div className="flex flex-col ml-4 truncate">
        <span className="text-lg">{name}</span>
        <span className="text-sm text-gray-500 overflow-hidden whitespace-nowrap text-ellipsis max-w-full">
          {/* {courseParticipants.map((user) => user.username).join(", ")} */}
          {description}
        </span>
        {/* <span className="text-sm text-green-500">escribiendo..</span> */}
      </div>
    </div>
  );
}

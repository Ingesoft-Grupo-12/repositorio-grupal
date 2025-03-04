"use client";

type ChatMessageCardProps = {
  message: string;
  time: string;
  isCurrentUser: boolean;
};

export default function ChatMessageCard({
  message,
  time,
  isCurrentUser,
}: ChatMessageCardProps) {
  return (
    <div
      className={`flex ${isCurrentUser ? "justify-end" : "justify-start"} mb-4`}
    >
      <div
        className={`max-w-xs p-3 rounded-lg ${
          isCurrentUser
            ? "bg-green-200 text-black self-end"
            : "bg-gray-100 text-black"
        }`}
      >
        <p>{message}</p>
        <span className="block text-xs text-gray-500 text-right mt-1">
          {time}
        </span>
      </div>
    </div>
  );
}

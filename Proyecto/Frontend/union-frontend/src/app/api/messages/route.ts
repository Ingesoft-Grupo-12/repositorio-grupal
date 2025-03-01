import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      messageId: 1,
      senderId: 1,
      content: "Hola",
      time: "2025-01-28 14:30:45",
    },
    {
      messageId: 2,
      senderId: 14,
      content: "Como estas?",
      time: "2025-02-28 14:30:45",
    },
    {
      messageId: 3,
      senderId: 1,
      content: "Bien",
      time: "2025-02-28 14:30:45",
    },
    {
      messageId: 4,
      senderId: 14,
      content: "Como estas?",
      time: "2025-02-28 14:30:45",
    },
  ]);
}

import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      messageId: 1,
      senderId: 1,
      content: "Hola",
      time: "11:00AM",
    },
    {
      messageId: 2,
      senderId: 14,
      content: "Como estas?",
      time: "1:00PM",
    },
    {
      messageId: 3,
      senderId: 1,
      content: "Bien",
      time: "6:00PM",
    },
  ]);
}

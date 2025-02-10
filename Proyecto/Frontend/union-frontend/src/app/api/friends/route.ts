import { NextResponse } from "next/server";

export async function GET() {
  return NextResponse.json([
    {
      userId: 1,
      userImage: "https://picsum.photos/id/100/48",
      userName: "John Doe",
      email: "jhondoe@unal.edu.co",
      requestStatus: "accepted",
    },
    {
      userId: 2,
      userImage: "https://picsum.photos/id/202/48",
      userName: "Pablo Gomez",
      email: "pablogomez@unal.edu.co",
      requestStatus: "accepted",
    },
  ]);
}

import { getSession } from "@auth0/nextjs-auth0/edge";
import { NextRequest, NextResponse } from "next/server";


export async function middleware(request: NextRequest) {
  const session = await getSession();
  if (!session || !session.user) {
    return NextResponse.redirect(new URL("api/auth/login", request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: ["/chats"],
};

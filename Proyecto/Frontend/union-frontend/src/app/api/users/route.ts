import { NextResponse } from "next/server";
import { getAccessToken } from "@auth0/nextjs-auth0";

const API_URL = process.env.WS_BASE_URL;

export async function GET() {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const res = await fetch(API_URL + "/union-backend/user/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    console.log(res);

    if (!res.ok) {
      throw new Error(`Error en la API externa: ${res.statusText}`);
    }

    const users = await res.json();
    return NextResponse.json(users);
  } catch (error) {
    if (error instanceof Error) {
      return NextResponse.json({ error: error.message }, { status: 500 });
    } else {
      return NextResponse.json({ error: "Unknown error" }, { status: 500 });
    }
  }
}

export async function POST(req: Request) {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const body = await req.json();

    if (!body.id || !body.username || !body.email || !body.role) {
      return NextResponse.json(
        { error: "Faltan datos requeridos en la petición." },
        { status: 400 }
      );
    }

    const res = await fetch(API_URL + "/union-backend/user", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
      body: JSON.stringify(body),
    });

    if (!res.ok) {
      throw new Error(`Error en la API externa: ${res.statusText}`);
    }

    const user = await res.json();
    return NextResponse.json(user, { status: 201 });
  } catch (error) {
    if (error instanceof Error) {
      return NextResponse.json({ error: error.message }, { status: 500 });
    } else {
      return NextResponse.json({ error: "Unknown error" }, { status: 500 });
    }
  }
}

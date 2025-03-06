import { NextResponse } from "next/server";
import { getAccessToken } from "@auth0/nextjs-auth0";

const API_URL = process.env.WS_BASE_URL;

export async function GET(
  request: Request,
  { params }: { params: { id: string } }
) {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const studentId = encodeURIComponent(params.id);

    const apiUrl = `${API_URL}/union-backend/api/enrollments/courses-with-last-message/${studentId}`;

    const res = await fetch(apiUrl, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!res.ok) {
      throw new Error(`Error en la API externa: ${res.statusText}`);
    }
    const courses = await res.json();

    return NextResponse.json(courses);
  } catch (error) {
    return NextResponse.json(
      { error: error instanceof Error ? error.message : "Unknown error" },
      { status: 500 }
    );
  }
}

export async function POST(req: Request) {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const body = await req.json();

    if (!body.code || !body.name || !body.description || !body.teacher) {
      return NextResponse.json(
        { error: "Faltan datos requeridos en la petición." },
        { status: 400 }
      );
    }

    const courseRes = await fetch(API_URL + "/union-backend/api/courses", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
      body: JSON.stringify(body),
    });

    if (!courseRes.ok) {
      throw new Error(`Error en la API externa: ${courseRes.statusText}`);
    }

    const createdCourse = await courseRes.json();

    const courseId = createdCourse.id;

    const enrollUser = async (userId: string) => {
      const userIdEncoded = encodeURIComponent(userId);
      const enrollmentUrl = `${API_URL}/union-backend/api/enrollments?courseId=${courseId}&studentId=${userIdEncoded}`;

      const enrollmentRes = await fetch(enrollmentUrl, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
      });

      if (!enrollmentRes.ok) {
        console.error(
          `Error al inscribir al usuario ${userId}:`,
          enrollmentRes.statusText
        );
      }
    };

    const teacherId = body.teacher.id;
    await enrollUser(teacherId);

    const enrollmentPromises = body.students.map((student: { id: string }) =>
      enrollUser(student.id)
    );

    await Promise.all(enrollmentPromises);

    return NextResponse.json(
      { message: "Curso creado y usuarios inscritos" },
      { status: 201 }
    );
  } catch (error) {
    return NextResponse.json(
      { error: error instanceof Error ? error.message : "Unknown error" },
      { status: 500 }
    );
  }
}

export async function DELETE(
  request: Request,
  { params }: { params: { id: string } }
) {
  try {
    const { accessToken } = await getAccessToken();

    if (!accessToken) {
      return NextResponse.json({ error: "No autorizado" }, { status: 401 });
    }

    const courseId = params.id;

    const apiUrl = `${API_URL}/union-backend/api/courses/${courseId}`;

    const res = await fetch(apiUrl, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    });

    if (!res.ok) {
      throw new Error(`Error en la API externa: ${res.statusText}`);
    }
    const response = await res.json();

    return NextResponse.json(response);
  } catch (error) {
    return NextResponse.json(
      { error: error instanceof Error ? error.message : "Unknown error" },
      { status: 500 }
    );
  }
}

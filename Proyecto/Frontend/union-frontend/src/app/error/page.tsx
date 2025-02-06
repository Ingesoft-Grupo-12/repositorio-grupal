"use client";

import { useSearchParams } from "next/navigation";

export default function ErrorPage() {
  const searchParams = useSearchParams();
  const message = searchParams.get("message") || "Ocurrió un error.";

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100">
      <h1 className="text-3xl font-bold text-red-600">Acceso denegado</h1>
      <p className="text-lg mt-4 text-gray-800">{message}</p>
      <a
        href="/api/auth/logout"
        className="mt-6 px-4 py-2 bg-blue-600 text-white rounded"
      >
        Volver a iniciar sesión
      </a>
    </div>
  );
}

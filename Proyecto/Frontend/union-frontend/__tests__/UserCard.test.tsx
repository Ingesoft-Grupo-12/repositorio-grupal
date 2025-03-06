import { render, screen } from "@testing-library/react";
import UserCard from "@/components/chats/UserCard/UserCard";
import "@testing-library/jest-dom";

const mockUser = {
  userId: 1,
  userImage: "https://picsum.photos/id/202/48",
  userName: "John Doe",
  userEmail: "john@example.com",
  requestStatus: "pending",
};

describe("Componente UserCard", () => {
  it("muestra correctamente la información del usuario", () => {
    render(<UserCard {...mockUser} />);

    // Verifica que el nombre y el email están en el documento
    expect(screen.getByText(mockUser.userName)).toBeInTheDocument();
    expect(screen.getByText(mockUser.userEmail)).toBeInTheDocument();

    // Verifica que la imagen tiene el alt correcto
    const image = screen.getByRole("img", {
      name: `${mockUser.userName}'s avatar`,
    });
    expect(image).toBeInTheDocument();
  });

  it("muestra una imagen por defecto cuando no hay imagen de usuario", () => {
    render(<UserCard {...mockUser} userImage="" />);

    // Asegura que se renderiza una imagen con el alt correcto
    const image = screen.getByRole("img", {
      name: `${mockUser.userName}'s avatar`,
    });

    expect(image).toBeInTheDocument();
    expect(image).toHaveAttribute("src"); // No verificamos la URL porque Next.js la transforma (next/Image)
  });

  it("maneja correctamente nombres y correos electrónicos largos", () => {
    const usuarioLargo = {
      ...mockUser,
      userName: "Johnathan Alexander Gomez García",
      userEmail: "johnathan.alexander.gomez.garcia@ejemplocorreolargo.com",
    };

    render(<UserCard {...usuarioLargo} />);

    // Verifica que el nombre y el email están presentes (truncate)
    expect(screen.getByText(usuarioLargo.userName)).toBeInTheDocument();
    expect(screen.getByText(usuarioLargo.userEmail)).toBeInTheDocument();
  });
});

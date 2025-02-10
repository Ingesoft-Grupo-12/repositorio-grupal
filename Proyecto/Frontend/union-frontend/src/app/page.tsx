import Image from "next/image";
import Navbar from "@/components/home/Navbar";
import LPFirst from "@/assets/images/landing-page-first.svg";
import LPSecond from "@/assets/images/landing-page-second.png";

export default async function Home() {
  return (
    <div className="min-h-screen bg-white text-black">
      <Navbar /> {/*componente cliente */}
      <main className="flex-1 m-10">
        <div className="max-w-7xl mx-auto px-4 py-8 sm:px-6 lg:px-8 bg-gray-200 rounded-lg">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="flex flex-col justify-center ml-10">
              <h1 className="text-4xl font-bold text-gray-800 mb-4">
                Bienvenido a UNión
              </h1>
              <p className="text-gray-600 text-lg">
                Conecta docentes y estudiantes de manera eficiente y segura con
                nuestra plataforma de mensajería instantánea diseñada
                exclusivamente para la educación.
              </p>
            </div>

            <div className="flex items-center justify-center">
              <Image
                src={LPFirst}
                alt="img chat"
                width={40}
                height={40}
                className="w-full max-w-sm rounded-lg shadow-lg"
              />
            </div>
          </div>
        </div>

        <div className="max-w-7xl mx-auto px-4 py-8 sm:px-6 lg:px-8 mt-8 bg-gray-200 rounded-lg">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div className="flex items-center justify-center">
              <Image
                src={LPSecond}
                alt="Modern web design"
                width={500}
                height={500}
                className="w-full max-w-sm rounded-lg shadow-lg"
              />
            </div>

            <div className="flex flex-col justify-center">
              <h1 className="text-4xl font-bold text-gray-800 mb-4">
                Herramientas modernas para la educación
              </h1>
              <p className="text-gray-600 text-lg">
                Nuestra plataforma aprovecha las últimas tecnologías para
                brindar una experiencia de comunicación fluida y segura. Con
                opciones personalizables y una interfaz intuitiva, UNión se
                adapta a las necesidades tanto de docentes como de estudiantes.
              </p>
            </div>
          </div>
        </div>
      </main>
      <footer>
        <div className="max-w-7xl mx-auto px-4 py-6 sm:px-6 lg:px-8">
          <p className="text-center text-sm">
            © {new Date().getFullYear()} UNión
          </p>
        </div>
      </footer>
    </div>
  );
}

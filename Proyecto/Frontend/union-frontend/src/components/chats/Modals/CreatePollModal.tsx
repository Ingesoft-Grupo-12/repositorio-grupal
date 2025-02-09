"use client";

import { useState } from "react";
import { FaPlus, FaTrash } from "react-icons/fa";
import { BsEmojiSmile } from "react-icons/bs";
import ReactPortal from "@/components/shared/ReactPortal";

type CreatePollModalProps = {
  isOpen: boolean;
  handleClose: () => void;
};

export default function CreatePollModal({
  isOpen,
  handleClose,
}: CreatePollModalProps) {
  const [question, setQuestion] = useState("");
  const [options, setOptions] = useState([""]);
  const [allowMultiple, setAllowMultiple] = useState(true);
  const [error, setError] = useState("");
  const [maxDateTime, setMaxDateTime] = useState("");

  const addOption = () => {
    if (options.length < 10) {
      setOptions([...options, ""]);
    }
  };

  const removeOption = (index: number) => {
    setOptions(options.filter((_, i) => i !== index));
  };

  const updateOption = (index: number, value: string) => {
    const newOptions = [...options];
    newOptions[index] = value;
    setOptions(newOptions);
  };

  const validatePoll = () => {
    if (question.trim() === "") {
      setError("La pregunta no puede estar vacía.");
      return false;
    }

    const validOptions = options.filter((opt) => opt.trim() !== "");
    if (validOptions.length < 2) {
      setError("Debes agregar al menos dos opciones de respuesta.");
      return false;
    }

    if (maxDateTime) {
      const selectedDateTime = new Date(maxDateTime).getTime();
      const currentDateTime = new Date().getTime();
      const oneMonthFromNow = new Date();
      oneMonthFromNow.setMonth(oneMonthFromNow.getMonth() + 2);

      if (selectedDateTime <= currentDateTime) {
        setError("La fecha y hora máxima debe ser en el futuro.");
        return false;
      }

      if (selectedDateTime > oneMonthFromNow.getTime()) {
        setError(
          "La fecha y hora máxima no puede ser mayor a 2 mes desde hoy."
        );
        return false;
      }
    }

    setError("");
    return true;
  };

  const handleSubmit = () => {
    if (!validatePoll()) {
      return;
    }

    const pollData = {
      question,
      options: options.filter((opt) => opt.trim() !== ""),
      allowMultiple,
      maxDateTime: maxDateTime || null,
    };
    console.log("Encuesta creada:", pollData);
    handleClose();
  };

  if (!isOpen) return null;

  return (
    <ReactPortal wrapperId="react-portal-modal-container">
      <div className="fixed top-0 left-0 w-screen h-screen z-40 flex items-center justify-center bg-black bg-opacity-50 text-black">
        <div className="bg-white rounded-lg shadow-lg w-full max-w-2xl p-6 lg:w-1/2 left-36">
          <div className="flex justify-between items-center mb-2">
            <h2 className="text-xl font-semibold">Crear Encuesta</h2>
            <button
              onClick={handleClose}
              className="text-gray-500 hover:text-gray-700"
            >
              ✖
            </button>
          </div>
          Pregunta
          <div className="relative mb-4 mt-3">
            <input
              type="text"
              placeholder="Escribe una pregunta para la encuesta."
              className="w-full p-2 pr-8 rounded border border-gray-700 focus:outline-none"
              value={question}
              onChange={(e) => setQuestion(e.target.value)}
            />
            <BsEmojiSmile className="absolute right-3 top-3 text-gray-400" />
          </div>
          Opciones
          <div className="mt-3">
            {options.map((option, index) => (
              <div key={index} className="flex items-center gap-2 mb-2">
                <input
                  type="text"
                  placeholder="Añade una opción."
                  className="w-full p-2 rounded border border-gray-700 focus:outline-none"
                  value={option}
                  onChange={(e) => updateOption(index, e.target.value)}
                />
                {options.length > 1 && (
                  <button
                    onClick={() => removeOption(index)}
                    className="hover:text-red-500"
                  >
                    <FaTrash />
                  </button>
                )}
              </div>
            ))}
          </div>
          {options.length < 10 && (
            <button
              onClick={addOption}
              className="flex items-center gap-2 text-gray-500 hover:text-gray-700 mt-2"
            >
              <FaPlus /> Añadir opción
            </button>
          )}
          <div className="mt-4 flex items-center gap-2">
            <input
              type="checkbox"
              checked={allowMultiple}
              onChange={() => setAllowMultiple(!allowMultiple)}
              className="w-5 h-5"
            />
            <label>Permitir varias respuestas</label>
          </div>
          <div className="mt-4">
            <label>Fecha y hora máxima para responder (opcional):</label>
            <input
              type="datetime-local"
              value={maxDateTime}
              onChange={(e) => setMaxDateTime(e.target.value)}
              className="w-full p-2 rounded border border-gray-700 focus:outline-none mt-2"
            />
          </div>
          {error && <p className="text-red-500 text-sm mt-2">{error}</p>}
          <button
            onClick={handleSubmit}
            className="w-full mt-4 bg-blue-500 hover:bg-blue-700 text-white py-2 rounded flex items-center justify-center gap-2"
          >
            Enviar
          </button>
        </div>
      </div>
    </ReactPortal>
  );
}

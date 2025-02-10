"use client";

import { AiFillFileImage, AiFillFileText } from "react-icons/ai";
import { MdPoll } from "react-icons/md";
import { useState } from "react";
import CreatePollModal from "../Modals/CreatePollModal";

type ClipMenuProps = {
  setIsPollModalOpen: (isOpen: boolean) => void;
};

export default function ClipMenu({ setIsPollModalOpen }: ClipMenuProps) {
  const [createPollModalOpen, setCreatePollModalOpen] = useState(false);

  const handleFileUpload = (accept: string) => {
    const input = document.createElement("input");
    input.type = "file";
    input.accept = accept;
    input.onchange = (event) => {
      const file = (event.target as HTMLInputElement).files?.[0];
      if (file) {
        console.log("Archivo seleccionado:", file.name);
      }
    };
    input.click();
  };

  return (
    <div className="absolute bottom-12 right-0 bg-white shadow-md rounded-lg p-2 flex flex-col">
      <button
        className="flex items-center p-2 hover:bg-gray-100"
        onClick={() => handleFileUpload("image/*,video/*")}
      >
        <AiFillFileImage size={20} className="mr-2" /> Multimedia
      </button>
      <button
        className="flex items-center p-2 hover:bg-gray-100"
        onClick={() => handleFileUpload("*")}
      >
        <AiFillFileText size={20} className="mr-2" /> Documento
      </button>
      <button
        className="flex items-center p-2 hover:bg-gray-100"
        onClick={() => {
          setCreatePollModalOpen(!createPollModalOpen);
          setIsPollModalOpen(true);
        }}
      >
        <MdPoll size={20} className="mr-2" /> Encuesta
      </button>

      {createPollModalOpen && (
        <CreatePollModal
          isOpen={createPollModalOpen}
          handleClose={() => {
            setCreatePollModalOpen(false);
            setIsPollModalOpen(false);
          }}
        />
      )}
    </div>
  );
}

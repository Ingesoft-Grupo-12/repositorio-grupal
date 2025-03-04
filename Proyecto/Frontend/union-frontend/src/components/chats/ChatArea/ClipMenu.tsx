"use client";

import { MdPoll } from "react-icons/md";
import { useState } from "react";
import CreatePollModal from "../Modals/CreatePollModal";

type ClipMenuProps = {
  setIsPollModalOpen: (isOpen: boolean) => void;
};

export default function ClipMenu({ setIsPollModalOpen }: ClipMenuProps) {
  const [createPollModalOpen, setCreatePollModalOpen] = useState(false);

  return (
    <div className="absolute bottom-12 right-0 bg-white shadow-md rounded-lg p-2 flex flex-col animate-fadeIn">
      <style jsx>{`
        @keyframes fadeIn {
          from {
            opacity: 0;
            transform: translateY(10px);
          }
          to {
            opacity: 1;
            transform: translateY(0);
          }
        }
        .animate-fadeIn {
          animation: fadeIn 0.3s ease-out;
        }
      `}</style>

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

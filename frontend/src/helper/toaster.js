import toastr from "toastr";
import { confirmAlert } from "react-confirm-alert";
import "react-confirm-alert/src/react-confirm-alert.css";
import React from "react";
import "../../node_modules/toastr/build/toastr.css";



// Custom Showprompt

export const MessageTypes = {
  Error: "error",
  Info: "info",
  Success: "success",
  Warning: "warning"
};

export const showAlert = (msg) => {
  toastrAlert(msg);
};

export const toastrAlert = (msg) => {
  toastr.options = {
    newestOnTop: false,
    closeButton: true,
    preventDuplicates: true,
    positionClass: "toast-top-center",
    timeOut: msg.msg_type === MessageTypes.Error ? 6000 : 3000,
    extendedTimeOut: 5000,
    ...msg
  };

  switch (msg.msg_type) {
    case MessageTypes.Error: {
      const title = msg.title === undefined ? "Error" : msg.title;
      toastr.error(msg.msg_text, title);
      break;
    }
    case MessageTypes.Success: {
      const title = msg.title === undefined ? "Success" : msg.title;
      toastr.success(msg.msg_text, title);
      break;
    }
    case MessageTypes.Warning: {
      const title = msg.title === undefined ? "Warning" : msg.title;
      toastr.warning(msg.msg_text, title);
      break;
    }
    case MessageTypes.Info: {
      const title = msg.title === undefined ? "Info" : msg.title;
      toastr.info(msg.msg_text, title);
      break;
    }
    default: {
    }
  }
};
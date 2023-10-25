import React from 'react';
import styles from './Button.module.css';
import { ReactComponent as LoadingSpinner } from "../../assets/images/loading-spinner.svg";

const Button = ({ type="button", Icon, loading = false, label, onClick, disabled = false, ...props }) => {
  const handleClick = (e) => {
    if (onClick && !disabled) {
      e.preventDefault();
      onClick(e);
    }
  }

  return (
    <button
      type={type}
      className={styles.button}
      onClick={handleClick}
      disabled={disabled || loading}
      {...props}
    >
      {props.children ? props.children : (
        <>
          {Icon && !loading ? <Icon className={styles.buttonIcon} /> : ''}
          {loading ? '' : label}
          {loading && <LoadingSpinner className={styles.loading} role="status" />}
        </>
      )}
    </button>
  );
};

export default Button;

import React from 'react';
import styles from './Input.module.css';

const Input = ({ label, defaultValue, onChange, ...props }) => {
  const handleChange = (e) => {
    onChange && onChange(e.target.value || null, e);
  }

  return (
    <div className={styles.inputContainer}>
      <label className={styles.label}>{label}</label>
      <input
        type="text"
        className={styles.input}
        defaultValue={defaultValue}
        aria-label={label}
        onChange={handleChange}
        { ...props }
      />
    </div>
  );
};

export default Input;

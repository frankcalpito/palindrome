import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import Input from './Input';

describe('Input Component', () => {
  it('renders input with label', () => {
    const { getByLabelText } = render(<Input label="Name" />);
    expect(getByLabelText('Name')).toBeInTheDocument();
  });

  it('sets initial value with defaultValue', () => {
    const { getByDisplayValue } = render(<Input defaultValue="John" />);
    expect(getByDisplayValue('John')).toBeInTheDocument();
  });

  it('calls the onChange callback when input value changes', () => {
    const onChange = jest.fn();
    const { getByLabelText } = render(<Input label="Name" onChange={onChange} />);
    const input = getByLabelText('Name');
    fireEvent.change(input, { target: { value: 'Jane' } });
    expect(onChange).toHaveBeenCalledWith('Jane');
  });
});

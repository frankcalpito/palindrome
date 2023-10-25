import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import Button from './Button';
import { ReactComponent as SearchSVG } from "../../assets/images/magnifying-glass.svg";

describe('Button Component', () => {
  it('renders a button with label', () => {
    const { getByText } = render(<Button label="Click Me" />);
    expect(getByText('Click Me')).toBeInTheDocument();
  });

  it('handles click event', () => {
    const onClick = jest.fn();
    const { getByText } = render(<Button label="Click Me" onClick={onClick} />);
    const button = getByText('Click Me');
    fireEvent.click(button);
    expect(onClick).toHaveBeenCalled();
  });

  it('shows the loading svg when loading is true', () => {
    const { container } = render(<Button label="Click Me" loading={true} />);
    const loading = container.querySelector('.loading');
    expect(loading).toBeInTheDocument();
  });

  it('renders an icon when provided', () => {
    const { container } = render(<Button Icon={SearchSVG} />);
    const icon = container.querySelector('.buttonIcon');
    expect(icon).toBeInTheDocument();
  });

  it('renders differently if children is passed', () => {
    const { container } = render(<Button><span className="custom">test</span></Button>);
    const button = container.querySelector('.custom');
    expect(button).toBeInTheDocument();
  });
});

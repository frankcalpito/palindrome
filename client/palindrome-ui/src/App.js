import React, { useState } from "react";
import "./App.css";
import palindromeService from "./services/palindromeService";
import Input from "./components/Input/Input";
import Button from "./components/Button/Button";

import { ReactComponent as SearchSVG } from "./assets/images/magnifying-glass.svg";

function App() {
  const [number, setNumber] = useState();
  const [result, setResult] = useState();
  const [loading, setLoading] = useState(false);

  const handleFindClosestPalindromeClick = async () => {
    setLoading(true);
    const closestPalindrome = await palindromeService.findClosestPalindrome(number);

    if (isNaN(closestPalindrome)) {
      alert(closestPalindrome);
    } else {
      setResult(closestPalindrome);
    }
    setLoading(false);
  }

  return (
    <div className="App">
      <h1>Palindrome</h1>
      <Input
        type="number"
        label="Number"
        onChange={(value) => {
          setNumber(value);
        }}
        role="region"
      />
      <Button
        onClick={handleFindClosestPalindromeClick}
        loading={loading}
        label="Find closest palindrome"
        Icon={SearchSVG}/>
      <div className={['result', result ? 'show' : null].join(' ')}>
        <span>The answer is:</span>
        <p>{result}</p>
      </div>
    </div>
  );
}

export default App;

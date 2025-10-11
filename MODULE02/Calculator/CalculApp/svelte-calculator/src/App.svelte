<script lang="ts">
  import { onMount } from 'svelte'
  let display = '0'

  function input(char: string) {
    if (display === '0' && char !== '.') display = char
    else display += char
  }

  function clear() {
    display = '0'
  }

  function del() {
    if (display.length === 1) display = '0'
    else display = display.slice(0, -1)
  }

  function evaluateExp() {
    try {
      if (!/^[0-9+\-*/(). ]+$/.test(display)) {
        display = 'Error'
        return
      }
     
      const fn = new Function('return ' + display)
      const res = fn()
      display = String(res)
    } catch (e) {
      display = 'Error'
    }
  }
</script>

<style>
  :global(body) {
    font-family: system-ui, -apple-system, 'Segoe UI', Roboto, 'Helvetica Neue', Arial;
    background: #0f172a;
    color: #e5e7eb;
    margin: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }
  .calc {
    width: 360px;
    background: linear-gradient(180deg,#0b1220, #111827);
    border-radius: 12px;
    box-shadow: 0 10px 30px rgba(2,6,23,0.7);
    padding: 18px;
  }
  .display {
    background: rgba(255,255,255,0.04);
    padding: 20px;
    border-radius: 8px;
    font-size: 32px;
    text-align: right;
    min-height: 64px;
    overflow-x: auto;
  }
  .keys {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 8px;
    margin-top: 14px;
  }
  button {
    padding: 18px 12px;
    font-size: 20px;
    border: none;
    border-radius: 8px;
    background: rgba(255,255,255,0.06);
    color: inherit;
    cursor: pointer;
  }
  button.op { background: linear-gradient(180deg,#f97316,#ea580c); color: white }
  button.eq { background: linear-gradient(180deg,#0ea5e9,#0369a1); color: white; grid-column: span 2 }
  button.zero { grid-column: span 2 }
</style>

<div class="calc">
  <div class="display" aria-live="polite">{display}</div>
  <div class="keys">
    <button on:click={clear}>AC</button>
    <button on:click={del}>DEL</button>
    <button on:click={() => input('%')}>%</button>
    <button class="op" on:click={() => input('/')}>÷</button>

    <button on:click={() => input('7')}>7</button>
    <button on:click={() => input('8')}>8</button>
    <button on:click={() => input('9')}>9</button>
    <button class="op" on:click={() => input('*')}>×</button>

    <button on:click={() => input('4')}>4</button>
    <button on:click={() => input('5')}>5</button>
    <button on:click={() => input('6')}>6</button>
    <button class="op" on:click={() => input('-')}>−</button>

    <button on:click={() => input('1')}>1</button>
    <button on:click={() => input('2')}>2</button>
    <button on:click={() => input('3')}>3</button>
    <button class="op" on:click={() => input('+')}>+</button>

    <button class="zero" on:click={() => input('0')}>0</button>
    <button on:click={() => input('.')}>.</button>
    <button class="eq" on:click={evaluateExp}>=</button>
  </div>
</div>

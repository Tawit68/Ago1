class CardMatchingGame {
    constructor() {
        this.cards = [];
        this.flippedCards = [];
        this.matchedPairs = 0;
        this.moves = 0;
        this.totalPairs = 8;
        this.isLocked = false;
        this.timer = 0;
        this.timerInterval = null;
        
        // Card symbols - Thai characters as requested
        this.cardSymbols = ['ก', 'ข', 'ค', 'ง', 'จ', 'ฉ', 'ช', 'ซ'];
        
        this.initializeGame();
        this.setupEventListeners();
    }
    
    initializeGame() {
        this.createCards();
        this.shuffleCards();
        this.renderBoard();
        this.resetGameStats();
    }
    
    createCards() {
        // Create pairs of cards
        this.cards = [];
        for (let i = 0; i < this.totalPairs; i++) {
            this.cards.push({ id: i, symbol: this.cardSymbols[i], matched: false });
            this.cards.push({ id: i, symbol: this.cardSymbols[i], matched: false });
        }
    }
    
    shuffleCards() {
        // Fisher-Yates shuffle algorithm
        for (let i = this.cards.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [this.cards[i], this.cards[j]] = [this.cards[j], this.cards[i]];
        }
    }
    
    renderBoard() {
        const gameBoard = document.getElementById('game-board');
        gameBoard.innerHTML = '';
        
        this.cards.forEach((card, index) => {
            const cardElement = document.createElement('div');
            cardElement.className = 'card';
            cardElement.dataset.index = index;
            
            const cardInner = document.createElement('div');
            cardInner.className = 'card-inner';
            
            const cardFront = document.createElement('div');
            cardFront.className = 'card-front';
            cardFront.textContent = card.symbol;
            
            const cardBack = document.createElement('div');
            cardBack.className = 'card-back';
            cardBack.textContent = '?';
            
            cardInner.appendChild(cardFront);
            cardInner.appendChild(cardBack);
            cardElement.appendChild(cardInner);
            
            gameBoard.appendChild(cardElement);
        });
    }
    
    setupEventListeners() {
        const gameBoard = document.getElementById('game-board');
        const restartBtn = document.getElementById('restart-btn');
        
        gameBoard.addEventListener('click', (e) => {
            const cardElement = e.target.closest('.card');
            if (cardElement && !this.isLocked) {
                this.flipCard(cardElement);
            }
        });
        
        restartBtn.addEventListener('click', () => {
            this.restartGame();
        });
    }
    
    flipCard(cardElement) {
        const index = parseInt(cardElement.dataset.index);
        const card = this.cards[index];
        
        // Don't flip if already flipped or matched
        if (cardElement.classList.contains('flipped') || cardElement.classList.contains('matched') || this.flippedCards.length >= 2) {
            return;
        }
        
        // Flip the card
        cardElement.classList.add('flipped');
        this.flippedCards.push({ element: cardElement, card: card, index: index });
        
        // Check for match when two cards are flipped
        if (this.flippedCards.length === 2) {
            this.moves++;
            document.getElementById('moves').textContent = this.moves;
            
            if (this.flippedCards[0].card.id === this.flippedCards[1].card.id) {
                this.handleMatch();
            } else {
                this.handleMismatch();
            }
        }
    }
    
    handleMatch() {
        // Mark cards as matched
        this.flippedCards.forEach(item => {
            item.element.classList.add('matched');
            this.cards[item.index].matched = true;
        });
        
        this.matchedPairs++;
        
        // Reset flipped cards
        this.flippedCards = [];
        
        // Check for win
        if (this.matchedPairs === this.totalPairs) {
            this.endGame();
        }
    }
    
    handleMismatch() {
        this.isLocked = true;
        
        // Wait before flipping back
        setTimeout(() => {
            this.flippedCards.forEach(item => {
                item.element.classList.remove('flipped');
            });
            
            this.flippedCards = [];
            this.isLocked = false;
        }, 1000);
    }
    
    startTimer() {
        this.timerInterval = setInterval(() => {
            this.timer++;
            document.getElementById('timer').textContent = this.timer;
        }, 1000);
    }
    
    stopTimer() {
        if (this.timerInterval) {
            clearInterval(this.timerInterval);
            this.timerInterval = null;
        }
    }
    
    resetGameStats() {
        this.moves = 0;
        this.timer = 0;
        this.matchedPairs = 0;
        this.flippedCards = [];
        this.isLocked = false;
        
        document.getElementById('moves').textContent = this.moves;
        document.getElementById('timer').textContent = this.timer;
        document.getElementById('message').textContent = '';
        
        this.stopTimer();
        this.startTimer();
    }
    
    endGame() {
        this.stopTimer();
        document.getElementById('message').textContent = `Congratulations! You won in ${this.moves} moves and ${this.timer} seconds!`;
    }
    
    restartGame() {
        this.stopTimer();
        this.initializeGame();
        this.resetGameStats();
    }
}

// Start the game when the page loads
document.addEventListener('DOMContentLoaded', () => {
    new CardMatchingGame();
});
# Cryptocurrency and Blockchain Technology: A Chronological Timeline

## 2008-2009: Bitcoin Genesis
- **October 2008**: Satoshi Nakamoto publishes the Bitcoin whitepaper "Bitcoin: A Peer-to-Peer Electronic Cash System"
- **January 3, 2009**: Bitcoin network goes live with the mining of the genesis block
- **January 12, 2009**: First Bitcoin transaction from Satoshi to Hal Finney

## 2010-2013: Early Bitcoin Era
- **May 2010**: First real-world Bitcoin transaction (10,000 BTC for two pizzas)
- **July 2010**: Mt. Gox exchange established
- **2011**: Rival cryptocurrencies begin to emerge (Litecoin, Namecoin)
- **2013**: Bitcoin price surpasses $1,000 for the first time
- **2013**: Ethereum whitepaper published by Vitalik Buterin
### Ethereum
* Decentralized blockchain platform
* Ability to run code(smart contracts)
* major blockchain innovations in recent years (DeFi, NFTs, DAOs) were first developed on Ethereum

### ETH
* Pay Gas Fees
## 2014-2016: Smart Contracts and Blockchain 2.0
- **July 2014**: Ethereum crowdsale raises 31,000 BTC
- **July 2015**: Ethereum network launches, introducing smart contracts to the mainstream
- **2016**: The DAO hack occurs, leading to Ethereum's hard fork into ETH and ETC
- **2016**: Enterprise interest in blockchain grows with formation of consortiums like R3 and Hyperledger

### Real-World Applications of Smart Contracts:
- DeFi (Decentralized Finance) : Lending, borrowing, and trading without traditional financial intermediaries
- NFTs (Non-Fungible Tokens) : Digital ownership certificates for art, collectibles, and other unique items
- DAOs (Decentralized Autonomous Organizations) : Organizations governed by code rather than central leadership
- Supply Chain Tracking : Verifiable records of product journeys from manufacturer to consumer
- Decentralized Insurance : Automated claims processing based on verifiable events

## 2017-2018: ICO Boom and Crypto Winter
- **2017**: Initial Coin Offering (ICO) boom begins
- **December 2017**: Bitcoin reaches nearly $20,000
- **December 2017**: CBOE and CME launch Bitcoin futures
- **2018**: Cryptocurrency market crash, beginning of "crypto winter"
- **2018**: Regulatory scrutiny of ICOs increases globally

## 2019-2020: DeFi Emergence and Institutional Interest
- **2019**: Decentralized Finance (DeFi) applications gain traction
- **May 2020**: Bitcoin's third halving event
- **2020**: Ethereum 2.0 Beacon Chain launches, beginning the transition to proof-of-stake
- **October 2020**: PayPal announces support for cryptocurrency
- **December 2020**: Bitcoin breaks previous all-time high
### Proof of Stake
* Proof of Stake (PoS) is a consensus mechanism used in blockchain networks like Ethereum.
* Instead of relying on miners to validate transactions, PoS systems use staked tokens (usually cryptocurrency) to validate transactions.
* Validators (nodes) compete to produce blocks by staking their tokens.
## 2021-2022: Mainstream Adoption and NFTs
- **February 2021**: Tesla announces $1.5 billion Bitcoin purchase
- **March 2021**: NFT boom with digital artist Beeple selling NFT for $69 million
- **April 2021**: Coinbase goes public on NASDAQ
- **October 2021**: First Bitcoin ETF in the US (ProShares Bitcoin Strategy ETF)
- **November 2021**: Bitcoin reaches all-time high near $69,000
- **2022**: Crypto market downturn following global economic uncertainty
- **September 2022**: Ethereum completes "The Merge" to proof-of-stake

## 2023-Present: Recovery and Spot ETFs
- **March 2023**: Banking crisis involving crypto-friendly banks (SVB, Signature)
- **June 2023**: SEC files lawsuits against major crypto exchanges
- **January 2024**: SEC approves spot Bitcoin ETFs in the United States
- **May 2024**: Bitcoin's fourth halving event
- **July 2024**: SEC approves spot Ethereum ETFs


# Blockchain Concepts

## Blockchain Fundamentals

### Blockchain
A blockchain is a distributed ledger database that maintains a continuously growing list of records (blocks) that are linked and secured using cryptography. Unlike traditional centralized databases:

- **Distributed Nature**: The database exists across multiple nodes (computers) in a network, with no central authority
- **Immutability**: Once data is written to a block and added to the chain, it cannot be altered without changing all subsequent blocks
- **Consensus Mechanisms**: Network participants agree on the state of the ledger through protocols like Proof of Work or Proof of Stake
- **Transparency**: All transactions are visible to anyone with access to the network

The blockchain can be accessed by RPC (Remote Procedure Call) nodes.

### RPC Nodes and Blockchain Interaction
For software engineers, think of RPC nodes as API endpoints that allow applications to interact with the blockchain. They serve as the interface between your application and the blockchain network.

#### RPC nodes offer two ways of communication:
- **RPC calls**: Synchronous request-response pattern similar to REST APIs
- **Websocket subscriptions**: Event-driven architecture where clients can subscribe to specific events on wallets and transactions

#### RPC nodes can be used to:
- Get the status of a transaction
- Check the balance of a wallet
- Make granular queries (You can get all transactions in a block, or you can get single transactions)
- Deploy and interact with smart contracts (on platforms like Ethereum)
- Broadcast new transactions to the network

#### Who Runs RPC Nodes:
- Public RPC providers : Services like Infura, Alchemy, QuickNode (for Ethereum)
- Blockchain projects : Official RPC endpoints provided by blockchain foundations
- Private entities : Companies running their own nodes for their applications
- Individuals : Tech-savvy users running full nodes with RPC capabilities enabled 

Think of it as a very robust bank ledger API. Everything your bank offers you, so can blockchain RPCs, but with additional capabilities specific to blockchain technology.

### Wallets
In traditional software terms, wallets are essentially user accounts with cryptographic security:

- Represented as public keys on the blockchain (similar to a username or account number)
- Accounts on the blockchain. Wallets can hold user's funds and the validity of the funds are always checked by the distributed blockchain
- Wallets are authenticated using a private key (similar to a password, but mathematically linked to the public key)
- Every transaction from the wallet is signed with the private key and then verified by the blockchain using the public key
- Unlike traditional authentication systems, private keys cannot be reset if lost - this is why secure key management is critical

From an implementation perspective, wallets can be:
- **Hot wallets**: Connected to the internet (mobile apps, web wallets)
- **Cold wallets**: Offline storage (hardware devices, paper wallets)
- **Custodial wallets**: Managed by a third party (like exchanges)
- **Non-custodial wallets**: User maintains full control of private keys

### Transactions
Transactions are how wallets communicate with the blockchain. For the purpose of this question, transactions are used to facilitate the movement of funds from one wallet to another.

From a software engineering perspective, transactions are structured data objects with specific properties:

Key transaction properties:
- Every transaction going to the blockchain has fees (gas) - think of this as the cost of computation and storage on the network
- Transactions from one public key are only valid when signed by its corresponding private key
- Transactions from the same wallet must be executed in order
- Each transaction submitted by the same wallet has a nonce attached (a sequential counter starting from 0)
- The chain will only execute a transaction with a particular nonce if all lower nonces have been executed
- It's important to make sure your transactions are sent in order, otherwise later nonces are stuck until earlier ones are executed (similar to database transaction isolation levels)
- All transactions are placed in a distributed priority queue (mempool) before being sent to the chain
- It's possible for multiple transactions from the same wallet to be in the pool
- If a transaction stays in the queue for too long without execution, it is dropped quietly from the queue
- You can increase the priority of the transaction by paying more transaction fees (similar to priority queues in distributed systems)

### Transaction Lifecycle
For software engineers, understanding the lifecycle of a transaction helps in building reliable blockchain applications:

1. **Creation**: Application creates and signs a transaction with the sender's private key
2. **Broadcast**: Transaction is sent to the network via an RPC node
3. **Mempool**: Transaction enters the memory pool (mempool) where it awaits inclusion in a block
4. **Validation**: Miners/validators verify the transaction's signature, nonce, and that the sender has sufficient funds
5. **Block Inclusion**: Transaction is included in a block by a miner/validator
6. **Confirmation**: Block is added to the blockchain (one confirmation)
7. **Finality**: Additional blocks are added on top, increasing the transaction's confirmation count and reducing the chance of reversal

### Common Challenges for Developers
When building blockchain applications, engineers often face these challenges:

- **Nonce Management**: Tracking and managing nonces for transactions, especially in high-frequency applications
- **Gas Estimation**: Determining the appropriate gas price and limit for transactions
- **Transaction Monitoring**: Tracking transaction status through confirmation stages
- **Error Handling**: Managing failed transactions and implementing retry mechanisms
- **Key Security**: Securely storing and managing private keys
- **Concurrency**: Handling multiple transactions from the same account simultaneously

More info here: [Ethereum Transactions Guide](https://www.quicknode.com/guides/ethereum-development/transactions/what-are-ethereum-transactions)
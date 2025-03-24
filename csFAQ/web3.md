# Web3 钱包涉及的技术

Web3 钱包是区块链和去中心化应用(DApps)生态系统中的关键组件，它涉及多种技术。以下是 Web3 钱包涉及的主要技术领域：

## 1. 密码学技术
- **非对称加密**：使用公钥和私钥对来保护用户资产
- **椭圆曲线密码学(ECC)**：如 secp256k1，用于生成密钥对
- **哈希算法**：如 SHA-256、Keccak-256 等
- **数字签名**：用于交易验证和身份认证

## 2. 区块链技术
- **区块链协议**：与以太坊、比特币、Solana 等区块链网络交互
- **智能合约**：与 ERC-20、ERC-721 等标准的代币合约交互
- **交易构建与广播**：创建、签名和发送交易到区块链网络
- **Gas 费用估算**：计算交易费用

## 3. 密钥管理
- **助记词(Mnemonic)**：BIP-39 标准，用于生成和恢复私钥
- **HD 钱包**：BIP-32/BIP-44 标准，用于分层确定性钱包
- **密钥派生**：从主密钥派生子密钥
- **密钥存储**：安全存储私钥的方法

## 4. 前端技术
- **Web 技术**：HTML、CSS、JavaScript
- **前端框架**：React、Vue.js、Angular 等
- **移动开发**：React Native、Flutter、原生 iOS/Android 开发
- **UI/UX 设计**：用户友好的界面设计

## 5. 后端技术
- **API 开发**：RESTful API、GraphQL
- **节点服务**：与区块链节点通信
- **数据索引**：区块链数据的索引和查询
- **缓存机制**：提高性能的数据缓存

## 6. 安全技术
- **安全存储**：安全存储私钥和敏感数据
- **生物识别**：指纹、面部识别等
- **多重签名**：需要多个签名才能执行交易
- **硬件安全模块(HSM)**：硬件级别的密钥保护

## 7. 网络通信
- **JSON-RPC**：与区块链节点通信的标准
- **WebSocket**：实时更新和通知
- **IPFS**：分布式文件存储
- **ENS(以太坊域名服务)**：人类可读的地址

## 8. Web3 库和工具
- **Web3.js/ethers.js**：与以太坊交互的 JavaScript 库
- **WalletConnect**：连接 DApps 和钱包的协议
- **MetaMask SDK**：与 MetaMask 钱包集成
- **Infura/Alchemy**：区块链节点提供商

## 9. 跨链技术
- **跨链桥**：在不同区块链之间转移资产
- **原子交换**：不同区块链之间的点对点交易
- **多链支持**：在一个钱包中管理多个区块链的资产

## 10. 隐私保护
- **零知识证明**：如 zk-SNARKs、zk-STARKs
- **混币技术**：提高交易隐私性
- **隐私币支持**：如 Monero、Zcash 等

## Android

## 2. 核心功能模块
### 钱包管理
- 创建/导入钱包（助记词、私钥、Keystore）
```
    用户 -> 创建新钱包/导入已有钱包 -> 
    |-> 新钱包: 生成随机熵 -> 创建助记词 -> 派生密钥对
    |-> 导入: 验证助记词/私钥/keystore -> 恢复密钥对
    -> 设置安全密码 -> 加密存储 -> 钱包创建完成
```  
- 多钱包管理
- HD 钱包支持（BIP-32/44/39）
- 地址簿管理
### 资产管理

- 多链资产展示（ETH、BTC、BNB 等）
- ERC-20 代币支持
- NFT 展示（ERC-721/1155）
- 资产价格和市值跟踪
### 交易功能
```
用户发起交易 -> 构建交易对象 -> 费用估算 -> 
用户确认 -> 生物识别验证 -> 
解密私钥 -> 签名交易 -> 广播到网络 -> 
监听交易状态 -> 更新UI
```
- 发送/接收加密货币
- 交易历史记录
- Gas 费用估算和调整
- 交易状态跟踪
### DApp 浏览器
- 内置 Web3 浏览器
- DApp 收藏夹
- WalletConnect 集成
- DApp 交互授权管理
### 安全功能
- 生物识别验证（指纹/面部识别）
- 交易签名确认
- 安全锁定设置
- 备份提醒


# Android Web3 Wallet Design

As a staff software engineer for a crypto company, I'll design an Android Web3 wallet with a focus on MVP first, followed by milestone planning.

## MVP Design (Detailed)

### 1. Core Architecture

The MVP will follow a clean architecture approach with these layers:
- **Presentation Layer**: UI components using Jetpack Compose
- **Domain Layer**: Business logic and use cases
- **Data Layer**: Repositories and data sources
- **Blockchain Layer**: Web3 interactions

### 2. Key Technologies

- **Kotlin & Coroutines**: For asynchronous operations
- **Web3j**: Java/Android library for Ethereum interaction
- **Room Database**: Local storage for wallet data
- **Biometric Authentication**: For secure access
- **Jetpack Compose**: Modern UI toolkit
- **MVVM Pattern**: For separation of concerns

### 3. MVP Features

#### Wallet Creation & Management
```
User -> Create/Import Wallet -> 
|-> New Wallet: Generate entropy -> Create mnemonic -> Derive key pairs
|-> Import: Validate mnemonic/private key -> Restore key pairs
-> Set security password -> Encrypt & store -> Wallet ready
```

- Support for creating a new wallet with BIP-39 mnemonic phrase
- Import existing wallet via mnemonic phrase or private key
- Secure storage of encrypted keys using Android Keystore
- Basic wallet backup functionality

##### 钱包实现步骤
1. 生成随机熵
2. 使用熵生成助记词
3. 从助记词派生种子
4. 使用种子生成HD钱包主密钥
5. 派生以太坊地址
##### 钱包实现代码示例
```kotlin
// 添加依赖:
// implementation 'org.web3j:core:4.9.4'
// implementation 'org.bitcoinj:bitcoinj-core:0.16.1'

import org.bitcoinj.crypto.MnemonicCode
import org.bitcoinj.crypto.HDKeyDerivation
import org.web3j.crypto.Credentials
import org.web3j.crypto.ECKeyPair
import org.web3j.utils.Numeric
import java.security.SecureRandom

class WalletGenerator {
    
    /**
     * 创建新钱包并生成BIP-39助记词
     * @return Pair<助记词列表, 钱包凭证>
     */
    fun createWalletWithMnemonic(): Pair<List<String>, Credentials> {
        // 1. 生成随机熵 (128位/16字节，生成12个单词)
        val entropy = ByteArray(16)
        SecureRandom().nextBytes(entropy)
        
        // 2. 使用熵生成助记词
        val mnemonicCode = MnemonicCode.INSTANCE
        val mnemonicList = mnemonicCode.toMnemonic(entropy)
        val mnemonicString = mnemonicList.joinToString(" ")
        
        // 3. 从助记词派生种子 (可选密码为空字符串)
        val seed = MnemonicCode.toSeed(mnemonicList, "")
        
        // 4. 使用种子生成HD钱包主密钥
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
        
        // 5. 派生以太坊地址 (BIP-44路径: m/44'/60'/0'/0/0)
        // 44' - BIP44目的
        // 60' - 以太坊币种
        // 0'  - 账户索引
        // 0   - 外部链
        // 0   - 地址索引
        val purpose = HDKeyDerivation.deriveChildKey(masterKey, 44 or 0x80000000.toInt())
        val coinType = HDKeyDerivation.deriveChildKey(purpose, 60 or 0x80000000.toInt())
        val account = HDKeyDerivation.deriveChildKey(coinType, 0 or 0x80000000.toInt())
        val change = HDKeyDerivation.deriveChildKey(account, 0)
        val addressKey = HDKeyDerivation.deriveChildKey(change, 0)
        
        // 6. 创建以太坊凭证
        val privateKeyBytes = addressKey.privKeyBytes
        val privateKey = Numeric.toBigInt(privateKeyBytes)
        val ecKeyPair = ECKeyPair.create(privateKey)
        val credentials = Credentials.create(ecKeyPair)
        
        return Pair(mnemonicList, credentials)
    }
    
    /**
     * 从助记词恢复钱包
     * @param mnemonicWords 助记词列表
     * @return 钱包凭证
     */
    fun recoverWalletFromMnemonic(mnemonicWords: List<String>): Credentials {
        // 从助记词派生种子
        val seed = MnemonicCode.toSeed(mnemonicWords, "")
        
        // 使用种子生成HD钱包主密钥
        val masterKey = HDKeyDerivation.createMasterPrivateKey(seed)
        
        // 派生以太坊地址 (BIP-44路径: m/44'/60'/0'/0/0)
        val purpose = HDKeyDerivation.deriveChildKey(masterKey, 44 or 0x80000000.toInt())
        val coinType = HDKeyDerivation.deriveChildKey(purpose, 60 or 0x80000000.toInt())
        val account = HDKeyDerivation.deriveChildKey(coinType, 0 or 0x80000000.toInt())
        val change = HDKeyDerivation.deriveChildKey(account, 0)
        val addressKey = HDKeyDerivation.deriveChildKey(change, 0)
        
        // 创建以太坊凭证
        val privateKeyBytes = addressKey.privKeyBytes
        val privateKey = Numeric.toBigInt(privateKeyBytes)
        val ecKeyPair = ECKeyPair.create(privateKey)
        
        return Credentials.create(ecKeyPair)
    }
}

// 创建新钱包
val walletGenerator = WalletGenerator()
val (mnemonicWords, credentials) = walletGenerator.createWalletWithMnemonic()

// 显示助记词和地址
println("助记词: ${mnemonicWords.joinToString(" ")}")
println("以太坊地址: ${credentials.address}")

// 恢复钱包
val recoveredCredentials = walletGenerator.recoverWalletFromMnemonic(mnemonicWords)
println("恢复的以太坊地址: ${recoveredCredentials.address}")
```
#### Basic Asset Management
- Display ETH balance
- Support for major ERC-20 tokens
- Transaction history for the main wallet address

#### Transaction Functionality
```
User initiates transaction -> Build transaction object -> Fee estimation -> 
User confirmation -> Biometric verification -> 
Decrypt private key -> Sign transaction -> Broadcast to network -> 
Monitor transaction status -> Update UI
```

- Send ETH and ERC-20 tokens
- Receive crypto (display QR code for address)
- View transaction status and history
- Basic gas fee settings

以太坊中的单位换算如下：

- 1 ETH = 10^18 Wei
- 1 Gwei = 10^9 Wei (常用于设置 Gas 价格)

##### Transaction Processing
```kotlin
class SendTransactionUseCase @Inject constructor(
    private val web3Service: Web3Service,
    private val securityManager: SecurityManager,
    private val walletRepository: WalletRepository
) {
    suspend fun execute(
        walletId: String,
        recipientAddress: String,
        amount: BigDecimal,
        password: String,
        gasPrice: BigInteger? = null,
        gasLimit: BigInteger? = null
    ): Result<String> {
        // Get wallet
        val wallet = walletRepository.getWallet(walletId) ?: 
            return Result.failure(Exception("Wallet not found"))
        
        // Decrypt private key
        val privateKey = securityManager.decryptPrivateKey(
            wallet.encryptedPrivateKey, 
            password
        ) ?: return Result.failure(Exception("Invalid password"))
        
        // Create credentials
        val credentials = Credentials.create(privateKey)
        
        // Prepare transaction
        val nonce = web3Service.getNonce(wallet.address)
        val finalGasPrice = gasPrice ?: web3Service.getGasPrice()
        val finalGasLimit = gasLimit ?: BigInteger.valueOf(21000)
        
        // Convert amount to Wei
        val valueInWei = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger()
        
        // Create and sign raw transaction
        val rawTransaction = RawTransaction.createEtherTransaction(
            nonce, finalGasPrice, finalGasLimit, recipientAddress, valueInWei
        )
        val signedTransaction = TransactionEncoder.signMessage(
            rawTransaction, credentials
        )
        
        // Send transaction
        val txHash = web3Service.sendRawTransaction(
            Numeric.toHexString(signedTransaction)
        )
        
        // Save transaction to local history
        walletRepository.saveTransaction(
            Transaction(
                hash = txHash,
                from = wallet.address,
                to = recipientAddress,
                value = amount.toString(),
                timestamp = System.currentTimeMillis(),
                status = TransactionStatus.PENDING
            )
        )
        
        return Result.success(txHash)
    }
}
```
#### Security
- Biometric authentication (fingerprint)
- PIN/password protection
- Encrypted storage of private keys
- Auto-lock functionality

### 5. UI Screens for MVP

1. **Onboarding**
   - Welcome screen
   - Create/Import wallet options
   - Security setup (PIN/biometrics)

2. **Main Dashboard**
   - Total portfolio value
   - List of assets with balances
   - Quick send/receive buttons

3. **Send Screen**
   - Address input (manual/QR scan)
   - Amount input
   - Fee settings
   - Confirmation screen

4. **Receive Screen**
   - QR code with wallet address
   - Copy address button

5. **Transaction History**
   - List of transactions
   - Status indicators
   - Transaction details view

6. **Settings**
   - Security settings
   - Backup options
   - Network settings

## Milestone 1 (Less Detailed)

Building on the MVP, Milestone 1 will expand functionality:

### 1. Enhanced Asset Management
- Multi-chain support (Bitcoin, Binance Smart Chain)
- Token discovery and management
- Basic NFT display (ERC-721)
- Price charts and market data

### 2. DApp Browser Integration
- Basic Web3 browser functionality
- Bookmarks for popular DApps
- WalletConnect protocol integration
- Transaction signing for DApp interactions

### 3. Advanced Security Features
- Multi-factor authentication
- Transaction limits
- Suspicious activity detection
- Recovery improvements

### 4. UX Improvements
- Customizable UI themes
- Transaction notifications
- Address book functionality
- Gas optimization suggestions

## Milestone 2 (High Level)

### 1. Advanced Features
- Cross-chain swaps
- DeFi integrations (staking, lending)
- Hardware wallet support
- Multi-signature wallet support

### 2. Ecosystem Expansion
- Custom DApp store
- Social recovery options
- Fiat on/off ramps
- Advanced portfolio analytics

### 3. Enterprise Features
- Team wallet management
- Compliance tools
- White-label solutions
- API for business integrations

## New Technologies Explained

### Web3j
Web3j is a lightweight Java and Android library for integrating with Ethereum blockchain. It handles the low-level RPC calls to Ethereum nodes, transaction creation, signing, and smart contract interactions. For our wallet, it will be the primary interface to the Ethereum blockchain.

### BIP Standards
- **BIP-39**: Defines the generation of mnemonic sentences (seed phrases) for deterministic wallets
- **BIP-32**: Hierarchical Deterministic Wallets - allows creating child keys from parent keys in a hierarchy
- **BIP-44**: Multi-Account Hierarchy - defines the structure for deterministic wallets across multiple coins

### WalletConnect
An open protocol that establishes a secure connection between mobile wallets and decentralized applications (DApps). It allows users to interact with DApps from their mobile wallet without exposing their private keys.

### Hardware Security Module (HSM)
While not immediately implemented in MVP, HSM provides hardware-level security for cryptographic operations. Android's Keystore system provides a software implementation of similar security features that we'll leverage initially.
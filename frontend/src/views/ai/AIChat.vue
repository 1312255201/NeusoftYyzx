<template>
  <el-container class="ai-chat-container">
    <!-- 左侧边栏 -->
    <el-aside width="300px" class="chat-aside">
      <div class="chat-header">
        <h2>东软颐养中心助手</h2>
        <p>您好！我是东软颐养中心的助手，有什么可以帮助您的吗？</p>
      </div>
      
      <div class="panel-content">
        <div class="function-group">
          <h4>快捷操作</h4>
          <el-button @click="clearChat" size="small" type="info" plain>
            <i class="el-icon-delete"></i> 清空对话
          </el-button>
        </div>
        
        <div class="function-group">
          <h4>常用问题</h4>
          <div class="quick-questions">
            <el-tag 
              v-for="(question, index) in quickQuestions" 
              :key="index"
              @click="askQuickQuestion(question)"
              class="question-tag"
              size="small"
            >
              {{ question }}
            </el-tag>
          </div>
        </div>
        
        <div class="function-group">
          <h4>护理服务</h4>
          <el-button @click="askAboutNursingServices" size="small" type="primary" plain>
            <i class="el-icon-s-help"></i> 了解护理项目
          </el-button>
        </div>
      </div>
    </el-aside>
    
    <!-- 主内容区域 -->
    <el-main class="chat-main">
      <div class="chat-messages" ref="messagesContainer">
      <div 
        v-for="(message, index) in messages" 
        :key="index" 
        :class="['message', message.type]"
      >
        <div class="message-avatar">
          <i :class="message.type === 'user' ? 'el-icon-user-solid' : 'el-icon-customer-service'"></i>
        </div>
        <div class="message-content">
          <div class="message-text">{{ message.content }}</div>
          <div class="message-time">{{ message.time }}</div>
        </div>
      </div>
      
      <!-- 加载状态 -->
      <div v-if="isLoading" class="message ai loading">
        <div class="message-avatar">
          <i class="el-icon-customer-service"></i>
        </div>
        <div class="message-content">
          <div class="message-text">
            <span class="loading-dots">AI正在思考中</span>
          </div>
        </div>
      </div>
      </div>
      
      <div class="chat-input">
        <el-input
          v-model="inputMessage"
          placeholder="请输入您的问题..."
          @keyup.enter="sendMessage"
          :disabled="isLoading"
          class="input-field"
        >
          <template #append>
            <el-button 
              type="primary" 
              @click="sendMessage"
              :disabled="isLoading || !inputMessage.trim()"
              icon="el-icon-s-promotion"
              style="color: white;"
            >
              发送
            </el-button>
          </template>
        </el-input>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import request from '@/request/request.js'

export default {
  name: 'AIChat',
  data() {
    const getCurrentTime = () => {
      const now = new Date()
      return `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`
    }
    
    return {
      messages: [
        {
          type: 'ai',
          content: '您好！我是东软颐养中心AI智能助手，很高兴为您服务。请问有什么可以帮助您的吗？',
          time: getCurrentTime()
        }
      ],
      inputMessage: '',
      isLoading: false,
      quickQuestions: [
        '如何预约护理服务？',
        '颐养中心有哪些设施？',
        '收费标准是什么？',
        '入住需要什么条件？',
        '有哪些康复项目？'
      ]
    }
  },
  methods: {
    async sendMessage() {
      if (!this.inputMessage.trim() || this.isLoading) {
        return
      }
      
      const userMessage = this.inputMessage.trim()
      
      // 添加用户消息
      this.messages.push({
        type: 'user',
        content: userMessage,
        time: this.getCurrentTime()
      })
      
      // 清空输入框
      this.inputMessage = ''
      
      // 设置加载状态
      this.isLoading = true
      
      // 滚动到底部
      this.$nextTick(() => {
        this.scrollToBottom()
      })
      
      try {
        // 调用后端AI接口
        const response = await request.get('/ai/chat', {
          params: {
            message: userMessage
          }
        })
        
        // 添加AI回复
        this.messages.push({
          type: 'ai',
          content: response || '抱歉，我暂时无法回答这个问题。',
          time: this.getCurrentTime()
        })
      } catch (error) {
        console.error('AI接口调用失败:', error)
        
        // 添加错误消息
        this.messages.push({
          type: 'ai',
          content: '抱歉，服务暂时不可用，请稍后再试。',
          time: this.getCurrentTime()
        })
      } finally {
        this.isLoading = false
        
        // 滚动到底部
        this.$nextTick(() => {
          this.scrollToBottom()
        })
      }
    },
    
    getCurrentTime() {
      const now = new Date()
      return `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}`
    },
    
    scrollToBottom() {
      const container = this.$refs.messagesContainer
      if (container) {
        container.scrollTop = container.scrollHeight
      }
    },
    
    clearChat() {
      this.messages = [
        {
          type: 'ai',
          content: '您好！我是AI智能助手，很高兴为您服务。请问有什么可以帮助您的吗？',
          time: this.getCurrentTime()
        }
      ]
    },
    
    askQuickQuestion(question) {
      this.inputMessage = question
      this.sendMessage()
    },
    
    askAboutNursingServices() {
      this.inputMessage = '请详细介绍一下颐养中心的护理服务项目和收费标准'
      this.sendMessage()
    }
  },
  
  mounted() {
    // 组件挂载后滚动到底部
    this.$nextTick(() => {
      this.scrollToBottom()
    })
  }
}
</script>

<style scoped>
.ai-chat-container {
  height: 90vh;
  background-color: #f5f5f5;
}

.chat-aside {
  background-color: white;
  border-right: 1px solid #e0e0e0;
  display: flex;
  flex-direction: column;
}

.chat-main {
  display: flex;
  flex-direction: column;
  background-color: white;
  padding: 0;
  height: 100%;
}

.chat-header {
  padding: 20px;
  background-color: #3ca2e0;
  color: white;
  text-align: center;
}

.chat-header h2 {
  margin: 0 0 8px 0;
  font-size: 24px;
  font-weight: 600;
}

.chat-header p {
  margin: 0;
  opacity: 0.9;
  font-size: 14px;
}

.chat-messages {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
  background: #f5f7fa;
  height: calc(100vh - 200px);
}

.message {
  display: flex;
  margin-bottom: 20px;
  animation: fadeIn 0.3s ease-in;
}

.message.user {
  flex-direction: row-reverse;
}

.message-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  color: white;
  margin: 0 12px;
}

.message.user .message-avatar {
  background: #3ca2e0;
}

.message.ai .message-avatar {
  background: #337ab7;
}

.message-content {
  max-width: 70%;
  min-width: 100px;
}

.message-text {
  padding: 12px 16px;
  border-radius: 18px;
  font-size: 14px;
  line-height: 1.4;
  word-wrap: break-word;
}

.message.user .message-text {
  background: #3ca2e0;
  color: white;
  border-bottom-right-radius: 4px;
}

.message.ai .message-text {
  background: white;
  color: #333;
  border: 1px solid #e4e7ed;
  border-bottom-left-radius: 4px;
}

.message-time {
  font-size: 12px;
  color: #909399;
  margin-top: 4px;
  text-align: center;
}

.message.user .message-time {
  text-align: right;
}

.message.ai .message-time {
  text-align: left;
}

.loading .message-text {
  background: white;
  border: 1px solid #e4e7ed;
}

.loading-dots {
  position: relative;
}

.loading-dots::after {
  content: '...';
  animation: dots 1.5s infinite;
}

@keyframes dots {
  0%, 20% {
    color: transparent;
    text-shadow: 0.25em 0 0 transparent, 0.5em 0 0 transparent;
  }
  40% {
    color: #333;
    text-shadow: 0.25em 0 0 transparent, 0.5em 0 0 transparent;
  }
  60% {
    text-shadow: 0.25em 0 0 #333, 0.5em 0 0 transparent;
  }
  80%, 100% {
    text-shadow: 0.25em 0 0 #333, 0.5em 0 0 #333;
  }
}

.chat-input {
  padding: 20px;
  background: white;
  border-top: 1px solid #e4e7ed;
}

.input-field {
  width: 100%;
}

.input-field .el-input__inner {
  border-radius: 4px;
  padding-left: 15px;
}

.input-field .el-input-group__append {
  border-radius: 0 4px 4px 0;
  border-left: none;
}

.input-field .el-button {
  background-color: #3ca2e0;
  border-color: #3ca2e0;
  transition: all 0.3s ease;
}

.input-field .el-button:hover {
  background-color: #337ab7;
  border-color: #337ab7;
  transform: translateY(-1px);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(10px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

/* 滚动条样式 */
.chat-messages::-webkit-scrollbar {
  width: 6px;
}

.chat-messages::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 3px;
}

.chat-messages::-webkit-scrollbar-thumb {
  background: #c1c1c1;
  border-radius: 3px;
}

.chat-messages::-webkit-scrollbar-thumb:hover {
  background: #a8a8a8;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .ai-chat-container {
    height: calc(100vh - 80px);
    margin: 0;
    border-radius: 0;
    flex-direction: column;
  }
  
  .chat-aside {
    width: 100% !important;
    height: 200px;
    border-right: none;
    border-bottom: 1px solid #e0e0e0;
  }
  
  .chat-main {
    flex: 1;
  }
  
  .panel-content {
    padding: 10px;
  }
  
  .quick-questions {
    display: flex;
    flex-wrap: wrap;
    gap: 5px;
  }
  
  .message-content {
    max-width: 85%;
  }
  
  .chat-header {
    padding: 15px;
  }
  
  .chat-messages {
    padding: 15px;
  }
  
  .chat-input {
    padding: 15px;
  }
}
</style>
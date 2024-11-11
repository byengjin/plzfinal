// 댓글 목록 불러오기
async function getReplies(postId) {
    try {
        const result = await axios.get(`/reply/${postId}`);
        console.log(result.data); // 데이터를 콘솔에 출력하여 확인
        return result.data;
    } catch (error) {
        console.error('Error fetching replies:', error);
        throw error;
    }
}

// 새로운 댓글 추가
async function addReply(replyObj, postId) {
    try {
        const response = await axios.post(`/reply/${postId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error adding reply:', error);
        throw error;
    }
}

// 대댓글 추가
async function addReReply(replyObj, parentId) {
    try {
        const response = await axios.post(`/reply/${parentId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error adding re-reply:', error);
        throw error;
    }
}

// 특정 댓글 불러오기
async function getReply(replyId) {
    try {
        const response = await axios.get(`/reply/${replyId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching reply:', error);
        throw error;
    }
}

// 댓글 수정
async function modifyReply(replyId, replyObj) {
    try {
        const response = await axios.put(`/reply/${replyId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error modifying reply:', error);
        throw error;
    }
}

// 댓글 삭제
async function removeReply(replyId) {
    try {
        const response = await axios.delete(`/reply/${replyId}`);
        return response.data;
    } catch (error) {
        console.error('Error removing reply:', error);
        throw error;
    }
}

// 전체 댓글과 대댓글 목록 불러오기
async function loadReplies(postId) {
    try {
        const response = await axios.get(`/reply/${postId}`);
        return response.data;
    } catch (error) {
        console.error('Error loading replies:', error);
        throw error;
    }
}

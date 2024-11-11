async function getList(replyId, postId, userId, content, regDate) {

    const result = await axios.get(`/reply/list/${replyId}`, {params: {page,size}})
    if(goLast){
        const total = result.data.total
        const lastPage =parseInt(Math.ceil(total/size))
    }
    // console.log(result.data)
    return result.data;
}

async function getList(replyId) {
    try {
        const result = await axios.get(`posting/read/${replyId}`);
        console.log(result.data);
        return result;
    } catch (error) {
        console.error('Error fetching list:', error);
        throw error;
    }
}

async function addReply(replyObj, postId) {
    try {
        const response = await axios.post(`/replies/${postId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error adding reply:', error);
        throw error;
    }
}

async function addReReply(replyObj, parentId) {
    try {
        const response = await axios.post(`reply/${parentId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error adding re-reply:', error);
        throw error;
    }
}

async function getReply(replyId) {
    try {
        const response = await axios.get(`/reply/${replyId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching reply:', error);
        throw error;
    }
}

async function modifyReply(replyId, replyObj) {
    try {
        const response = await axios.put(`/reply/${replyId}`, replyObj);
        return response.data;
    } catch (error) {
        console.error('Error modifying reply:', error);
        throw error;
    }
}

async function removeReply(replyId) {
    try {
        const response = await axios.delete(`/reply/${replyId}`);
        return response.data;
    } catch (error) {
        console.error('Error removing reply:', error);
        throw error;
    }
}

async function loadReplies(postId) {
    try {
        const response = await axios.get(`/replies/${postId}`);
        return response.data;
    } catch (error) {
        console.error('Error loading replies:', error);
        throw error;
    }
}
